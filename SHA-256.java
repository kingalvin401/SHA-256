import java.io.*;
import java.security.MessageDigest;

class Dictionary{
     
    private String input[]; 

    public Dictionary(){
        input = load("C:\\Users\\alvin\\OneDrive\\Desktop\\Projects\\SHA256\\output1.txt");  
    }
    
    public int getSize(){
        return input.length;
    }
    
    public String getWord(int n){
        return input[n];
    }
    
    private String[] load(String file) {
        File aFile = new File(file);     
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader( new FileReader(aFile) );
            String line = null; 
            int i = 0;
            while (( line = input.readLine()) != null){
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        }catch (FileNotFoundException ex){
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }finally{
            try {
                if (input!= null) {
                    input.close();
                }
            }catch (IOException ex){
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(String s: array){
            s.trim();
        }
        return array;
    }
}


public class SHA256 
{


	public static void main(String[] args)  
    {

	
		Dictionary dict = new Dictionary();
		
		int numLines = dict.getSize();
			
		
		int highest = 0;
		String lineA = ""; String lineB = "";
		for(int i = 0; i < numLines; i++) {
			for(int j = 0; j < numLines; j++) {
				if(i !=j) {
					String lineX = dict.getWord(i), lineZ = dict.getWord(j);
                    int comp = compare(sha256(lineX),sha256(lineZ));
					if(highest < comp) 
                    {
						highest = comp;
						lineA = lineX;
						lineB = lineZ;
					}
				}
			}
		}
        
		
		System.out.println(highest);
        System.out.println(sha256(lineA));
        System.out.println(lineA);
        System.out.println(sha256(lineB));
        System.out.println(lineB);
        
	}
    public static String sha256(String input)
    {
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        }catch(Exception e){
            return(e.toString());
        }
    }

	public static int compare(String s1, String s2) 
   {
    	int count = 0;
        
    	for(int i = 0; i < s1.length(); i++)
        {
    		if ( s1.charAt(i)  == s2.charAt(i))count++;
            
    	}
        return count;
    }
	
}





	




