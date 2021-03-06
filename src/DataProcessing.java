import java.util.Enumeration;

import java.util.Vector;

import javax.swing.JFrame;

import java.io.*;
import java.net.*;
import java.sql.*;


public  class DataProcessing {
	
	 
	private static Socket socket;
	private static DataInputStream in;
	private static ObjectOutputStream out;
    private static FileOutputStream fos;
    private static String downpath = "E:/downloadpath/";
	
	
	
    
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	@SuppressWarnings("unused")
	private static ResultSetMetaData metaData;
	@SuppressWarnings("unused")
	private static int numberOfRows;
	
	
	private static boolean connectedToDatabase = false;
	
	
	public static void connectToDatabase(String driverName, String url, String user, String password) throws Exception
	  {
			Class.forName(driverName);		// 加载数据库驱动类
			
	      connection=DriverManager.getConnection(url, user, password);   // 建立数据库连接
	      connectedToDatabase = true;
	  }
	
	
	
	public static void disconnectFromDatabase(){
		if ( connectedToDatabase ){
			// close Statement and Connection
			try{                                            
		            resultSet.close();                        
		            statement.close();                        
		            connection.close();                       
		       }catch ( SQLException sqlException ){                                            
		            sqlException.printStackTrace();           
		       }finally{                                            
		            connectedToDatabase = false;              
		       }
			} 
	}
	
	
	
	
	
	
	
	

	
	public static  void Init() throws IOException{
		
		socket =new Socket("127.0.0.1",1234);

	}
	
	
	
	

	public static Enumeration<Doc> getAllDocs() throws SQLException,IllegalStateException{		
		Vector <Doc> docs = new Vector<Doc>();
		Doc temp=null; 
		if ( !connectedToDatabase ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		statement = connection.createStatement( 
		         ResultSet.TYPE_SCROLL_INSENSITIVE,
		         ResultSet.CONCUR_READ_ONLY );
		String sql="select * from doc_info";
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()){
			String ID=resultSet.getString("id");
			String creator=resultSet.getString("creator");
			Timestamp timestamp=resultSet.getTimestamp("time");
			String description=resultSet.getString("description");
			String filename=resultSet.getString("filename");
			temp =new Doc(ID,creator, timestamp, description, filename);
			docs.addElement(temp);
			
		}
		
		return docs.elements();	

	} 
	

	
	public static User searchUser(String name, String pwd) throws SQLException,IllegalStateException {
		User temp= null;
		if ( !connectedToDatabase ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		statement = connection.createStatement( 
		         ResultSet.TYPE_SCROLL_INSENSITIVE,
		         ResultSet.CONCUR_READ_ONLY );
		String sql="select * from user_info where name='"+name+"' and password='"+pwd+"'";
		
		
		resultSet = statement.executeQuery(sql);
		
		if (resultSet.next()){
			String username=resultSet.getString("name");
			String password1=resultSet.getString("password");
			String role=resultSet.getString("role");
			
			if (role.equals("administrator")){
				temp = new Administrator(username,password1,role);
			}else if(role.equals("operator")){
				temp = new Operator(username,password1,role);
			}else
				temp = new Browser(username,password1,role);
				
		}
		
		return temp;
	}
	
	
	
	
	public static Enumeration<User> getAllUser() throws SQLException,IllegalStateException{
	Vector <User> users = new Vector<User>();
		User temp=null; 
		if ( !connectedToDatabase ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		statement = connection.createStatement( 
		         ResultSet.TYPE_SCROLL_INSENSITIVE,
		         ResultSet.CONCUR_READ_ONLY );
		String sql="select * from user_info";
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()){
			String username=resultSet.getString("name");
			String password=resultSet.getString("password");
			String role=resultSet.getString("role");
			
			if (role.equals("administrator")){
				temp = new Administrator(username,password,role);
			}else if(role.equals("operator")){
				temp = new Operator(username,password,role);
			}else
				temp = new Browser(username,password,role);
			
			users.addElement(temp);
		}
		
		return users.elements();	
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException,IllegalStateException{
		if ( !connectedToDatabase ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		String sql = "UPDATE user_info SET password=?, role=? where name=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, role);
		preparedStatement.setString(3, name);
		
		int temp =preparedStatement.executeUpdate();
		
		if (temp!=0)
			return true;
		else
			return false;	
		}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException,IllegalStateException{
		if ( !connectedToDatabase ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		String sql = "INSERT INTO user_info (name,password,role) VALUES(?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, role);

		
		int temp =preparedStatement.executeUpdate();
		
		if (temp!=0)
			return true;
		else
			return false;
	}
	
	
	
	
	public static boolean deleteUser(String name) throws SQLException,IllegalStateException{
	if ( !connectedToDatabase ) 
        throw new IllegalStateException( "Not Connected to Database" );
		
	String sql = "DELETE FROM  user_info where name=?";
	preparedStatement = connection.prepareStatement(sql);
	preparedStatement.setString(1, name);
			
	int temp =preparedStatement.executeUpdate();
	
	if (temp!=0)
		return true;
	else
		return false;
		
	}	
            
   public static void searchDoc(String id) throws Exception {
	   
	ObjectOutputStream out =new ObjectOutputStream (socket.getOutputStream());	 
	NetTransfer ntOut=new NetTransfer();
	ntOut.action="download";
	 int a = Integer.parseInt(id);
	ntOut.id=a;
    out.writeObject(ntOut);
  
    
   in =new DataInputStream(socket.getInputStream());
	
   String fileName = in.readUTF();
   long fileLength = in.readLong();
   fos =new FileOutputStream(new File(downpath + fileName));
   byte[] sendBytes =new byte[1024];
   int transLen =0;
   System.out.println("----开始接收文件<" + fileName +">,文件大小为<" + fileLength +">----");
   while(true){
       int read =0;
       read = in.read(sendBytes);
       if(read <=0)
           break;
       transLen += read;
       System.out.println("正在下载，请稍候....");
       fos.write(sendBytes,0, read);
       fos.flush();
   }
   System.out.println("----接收文件<" + fileName +">成功-------");
   if(in !=null)
      in.close();
   if(fos !=null)
       fos.close();

	   
   }

	public static void insertDoc(String path, String creator,  Timestamp timestamp, String description, String filename, long filelength) throws  IOException{
		ObjectOutputStream out =new ObjectOutputStream (socket.getOutputStream());
		
		NetTransfer ntOut=new NetTransfer();
		ntOut.action="upload";
		ntOut.description=description;
		ntOut.fileName=filename;
		ntOut.time=timestamp;
		ntOut.filelength=filelength;
		out.writeObject(ntOut);
		
	      File file =new File(path);
	      FileInputStream   fis =new FileInputStream(file);
	      DataOutputStream dos =new DataOutputStream(socket.getOutputStream());
	  
	      byte[] sendBytes =new byte[1024];
	      int length =0;
	      try {
				while((length = fis.read(sendBytes)) !=-1){
				    dos.write(sendBytes,0, length);
				    dos.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      finally{
	          if(fis !=null)
	              fis.close();
	          if(dos !=null)
	              dos.close();
	          
	      }
		
		
		
	} 
	
   public static int shutdown() throws Exception {
	   
	   ObjectOutputStream out =new ObjectOutputStream (socket.getOutputStream());
		
		NetTransfer ntOut=new NetTransfer();
		ntOut.action="shutdown";
		out.writeObject(ntOut);
	   
	   
	   return JFrame.EXIT_ON_CLOSE;
   }
   
	
	public static void main(String[] args) {
		}
	}