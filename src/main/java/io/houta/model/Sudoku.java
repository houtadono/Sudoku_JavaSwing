package io.houta.model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Sudoku{
    
    private int size;
    private List<String> sudo ;
    private List<String> solution ;

    private static int solanrequest = 1;

    public Sudoku(int size){
        this.size = size;
        creatrMatric();
    }

    public void creatrMatric(){
        sudo = new ArrayList<>();
        solution = new ArrayList<>();
        String url = "https://www.sudokuweb.org/";
        try{
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();  
            conn.setRequestMethod("POST");
            String data = String.format("sign2=%dx%d", this.size,this.size);
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(data);
            wr.close();
            int responseC = conn.getResponseCode();
            System.out.println("Sending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + data);
            System.out.println("Response Code : " + responseC);
            
            if(responseC != 200){
                System.out.println("Khong the request");
                return;
            }
        
            BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
        
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String content = response.toString();
            String []cell = content.split("id=\"right");

            for(int i = 1 ; i < cell.length; i++){
                if(cell[i].contains("sedy")){
                    sudo.add(cell[i].split("</span")[0].split("sedy\">")[1]);
                    solution.add(sudo.get(sudo.size()-1));
                }else{
                    sudo.add(null);
                    solution.add(cell[i].split("</span")[0].split("true\">")[1]);
                }
            }

            System.out.println("so lan request: "+(solanrequest++));
        }catch(Exception e){

        }
    }

    public String getCell(int i, int j){
        return this.sudo.get(i*size+j);
    }

    public String getHideCell(int i, int j){
        return this.solution.get(i*size+j);
    }

    // public static void main(String[] args) throws Exception{
    //     Sudoku a = new Sudoku(9);
    //     a.creatrMatric();
    //     System.out.println(a);
    // }
}