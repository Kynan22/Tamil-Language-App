package au.com.codycodes.tpk.tamizhpallikoodam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData extends AppCompatActivity{

    ArrayAdapter<String> adapter;
    String host ="http://10.0.2.2:63343/tpk_db/connect.php";
    InputStream is = null;
    String line=null;
    String result=null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new Connection().execute();
        finish();
    }

    class Connection extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params){
            try{
                URL url = new URL(host);
                HttpURLConnection con =(HttpURLConnection) url.openConnection();

                is=new BufferedInputStream(con.getInputStream());
            }
            catch (Exception e){
                e.printStackTrace();
                Log.i("test1", "First Try Failed");
            }

            try{

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                while((line = br.readLine()) != null){
                    sb.append(line+"\n");
                }

                is.close();
                result=sb.toString();

            }catch (Exception e){
                e.printStackTrace();
                Log.i("test2", "Second Try Failed");

            }

            try{
                JSONArray ja = new JSONArray(result);
                result = ja.getString(0);
                result = result.replace("{\"highscore\":\"", "");
                result = result.replace("\"}", "");

            }
            catch(Exception e){
                e.printStackTrace();
                Log.i("test3", "Third Try Failed");

                result = "didnt work";
            }

            return result;


        }

        @Override
        protected void onPostExecute(String result){
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            finish();


        }
    }
}
