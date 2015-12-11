import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPFilter {

	public IPFilter(){
		
	}
	public static void main(String[] args) throws UnknownHostException {
		String       s = "10.1.1.99";
		Inet4Address a = (Inet4Address) InetAddress.getByName(s);
		byte[]       b = a.getAddress();
		
//		for( byte item : b ){
//			System.out.println( item );
//		}
		
		System.out.println( (b[0] & 0xFF) << 24 );
		
		int          i = ((b[0] & 0xFF) << 24) |
		                 ((b[1] & 0xFF) << 16) |
		                 ((b[2] & 0xFF) << 8)  |
		                 ((b[3] & 0xFF) << 0);
		
		System.out.println( 2 << 0 );
		System.out.println( 2 << 1 );
		System.out.println( 2 << 2 );
		System.out.println( 2 << 3 );
		System.out.println( 2 << 4 );
		System.out.println( 2 << 9 );
		System.out.println( 2 << 10 );
		
		System.out.println();
		System.out.println((b[0] & 0xFF) << 24);
		System.out.println((b[1] & 0xFF) << 16);
		System.out.println((b[2] & 0xFF) << 8);
		System.out.println((b[3] & 0xFF) << 0);
		
	}
	
}
