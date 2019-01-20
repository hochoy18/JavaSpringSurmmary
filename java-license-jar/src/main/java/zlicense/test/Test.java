package zlicense.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/1/18
 */
public class Test {
    public static void main(String[] args) {
        String cm = "app";
        String cm1 = "web";
        String cm2 = "mini";
        System.out.println(mapTest(cm));
        System.out.println(mapTest(cm1));
        System.out.println(mapTest(cm2));
//        dateTest();
//        downloadFromURL("https://github.com/hochoy18/free-programming-books-zh_CN/commit/97531a1bbb3c9c83f1fe3438bf63335df8f07626#diff-a1ee87dafebc22cbd96979f1b2b7e837",
//                "test111","E:\\work\\wbkit\\project\\Cobub-Java\\JavaSpringSurmmary\\java-license-jar\\license_files");
    }

    static void downloadFromURL(String urlStr, String fileName, String SavePath) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream in = conn.getInputStream();
            byte[] getData = readInputStream(in);
            File saveDir = new File(SavePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (MalformedURLException e) {

        } catch (IOException e) {
        }

    }

    static byte[] readInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            return bos.toByteArray();
        } catch (Exception e) {

        }
        return null;
    }


    static void dateTest() {
        String beginTime = "2018-07-28";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
//            String endTime = format.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            Date date1 = format.parse(beginTime);

            Date date2 = format.parse(format.format(new Date()));
//            Date date2 = format.parse(endTime);

            boolean before = date1.before(date2);

            System.out.println(before);

        } catch (ParseException e) {

            e.printStackTrace();

        }
    }

    static boolean mapTest(String cModelName) {
        boolean flag = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<ModelAndTimes<String, String, String>> modelAndTimes = getClientModelAndTime();
        for (ModelAndTimes<String, String, String> tuple : modelAndTimes) {
            while (tuple.modelName.equals(cModelName)) {
                //TODO 时间 对比
                try {
                    Date client_end_date = format.parse(format.format(new Date()));
                    Date model_end_date = format.parse(tuple.endTime);
                    if (client_end_date.before(model_end_date)) {
                        System.out.println("success .............");
                        flag = true;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return flag;
    }

    public static List<ModelAndTimes<String, String, String>> getClientModelAndTime() {

        List<ModelAndTimes<String, String, String>> clientMAT = new ArrayList<ModelAndTimes<String, String, String>>();
        clientMAT.add(new ModelAndTimes<String, String, String>("app", "2019-01-01", "2019-12-30"));
        clientMAT.add(new ModelAndTimes<String, String, String>("web", "2019-01-01", "2019-01-17"));
        clientMAT.add(new ModelAndTimes<String, String, String>("mini", "2019-01-01", "2019-01-19"));
        return clientMAT;
    }
}

class ModelAndTimes<A, B, C> {
    public final A modelName;

    public final B startTime;

    public final C endTime;

    public ModelAndTimes(A modelName, B startTime, C endTime) {
        this.modelName = modelName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}