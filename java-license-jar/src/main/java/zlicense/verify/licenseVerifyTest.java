package zlicense.verify;

public class licenseVerifyTest {
	public static void main(String[] args){
		VerifyLicense vLicense = new VerifyLicense();
		try{
			String path = "E:\\work\\wbkit\\project\\Cobub-Java\\JavaSpringSurmmary\\java-license-jar\\src\\main\\resources\\verifyparam.properties";
			vLicense.setParam(path);
			
			vLicense.verify();
		}
		catch(Exception er){
			er.printStackTrace();
		}

	}
}
