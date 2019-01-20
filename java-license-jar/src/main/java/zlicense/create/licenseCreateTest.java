package zlicense.create;



public class licenseCreateTest {
	public static void main(String[] args){
		CreateLicense cLicense = new CreateLicense();
		String path = "E:/work/wbkit/project/Cobub-Java/JavaSpringSurmmary/java-license-jar/src/main/resources/createparam.properties";
		cLicense.setParam(path);
		//
		cLicense.create();
	}
}
