
package pdfsignature;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Set;
public class Pdfsignature {

     public static final String SRC = "/home/sanjaynegi/Form MGT-7-041215_3.pdf";
     public static final String DEST = "/home/sanjaynegi/Form MGT-7-041215_31.pdf";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Pdfsignature().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        AcroFields fields = reader.getAcroFields();
        
       ArrayList<String> signatures = fields.getSignatureNames(); 
        for (String signature : signatures)
       {
       
       // Start revision extraction
            OutputStream oos = new FileOutputStream(DEST);
            byte bb[] = new byte[8192];
            java.io.InputStream ip = fields.extractRevision(signature);
            
             System.out.println("ip"+ip);
            int n = 0;
            while ((n = ip.read(bb)) > 0)
                oos.write(bb, 0, n);
            oos.close();
            ip.close();
           
       }
    }
    
}
