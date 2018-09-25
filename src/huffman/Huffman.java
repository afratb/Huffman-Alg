/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;

class nesne{
    public char karakter;
    public int sayi;
}

class agac{
    nesne harf;
    agac sol;
    agac sag;
    int deger;
}
 

public class Huffman {
    public static ArrayList<nesne> lst = new ArrayList();
    
    public static void main(String[] args) {
        //karakterlerden düğüm yapıp frekanslarını belirleme
        Boolean bul=false;
        char[] dizi = {'a','f','r','a','a','f','b'};
        for(int i = 0;i<dizi.length;i++){
            
            if(lst.size()==0 || bul==false){
                nesne obj = new nesne();
                obj.sayi=1;
                obj.karakter=dizi[i];
                lst.add(obj);
                bul=true;
            }
            else{
            for(int j=0;j<lst.size();j++){
                if(dizi[i]==lst.get(j).karakter){
                    lst.get(j).sayi++;
                    bul=true;
                    break;
                }
                else bul=false;
            }
            if(bul==false){
                nesne obj = new nesne();
                obj.sayi=1;
                obj.karakter=dizi[i];
                lst.add(obj);
                bul=true;
            }
            }
            
        }
        //agaca ekleme
        agac agc = new agac();
        int topla=0;
        nesne kucuk = null;
        int kck=100;
        for(;lst.size()!=0;){
        for(int i=0;i<lst.size();i++){
            if(lst.get(i).sayi<kck){
                kucuk=lst.get(i);
                kck=lst.get(i).sayi;
            }
        }
        if(agc.sag==null)agc.sag=dugum_olustur(kucuk, kck);
        else if(agc.sol==null) agc.sol=dugum_olustur(kucuk, kck);
        else 
        {
            agac tmp = new agac();  
            tmp.sag=agc; 
            agc=tmp;
            agc.sol=dugum_olustur(kucuk, kck);
        }
        topla+=kck;
        lst.remove(kucuk);
        kck=100;
        }
        for(int i=0;i<dizi.length;i++)
        sıkıstırma(agc,dizi[i] );
        
        Acma(agc);
    }
    
   public static void sıkıstırma(agac tmp,char c){

       agac gecici=tmp;
       while(gecici!=null){
           if(gecici.harf==null){
               if(gecici.sol!=null){
                   if(gecici.sol.harf.karakter==c){
                       System.out.print(0);
                       return;
                   }
                else if(gecici.sag!=null){
                       System.out.print(1);
                       sıkıstırma(gecici.sag, c);
                       return;
                }
               }
           }
           else break;
           }
   }
    public static agac dugum_olustur(nesne n,int t){
        agac agc = new agac();
        agc.harf=n;
        agc.sag=null;
        agc.sol=null;
        agc.deger=n.sayi;
        return agc;
    }
    
    public static void Acma(agac tmp){
        System.out.println(" ");
        char[] dizi = {'0','1','0','1','1','1','0','0','1','0','1','1','0'};
        agac dene = tmp;
        for(int i=0;i<dizi.length;i++){
            while(dene!=null){
            if(dizi[i]=='0'){
                if(dene.sol.harf!=null){
                    System.out.print(dene.sol.harf.karakter); 
                    dene=tmp; 
                    break;}
                
            }
            else if(dizi[i]=='1' && dene.sag.harf!=null){
                System.out.print(dene.sag.harf.karakter); 
                    dene=tmp;
                    break;
            }
            else{
                dene=dene.sag; break;
            }
            
        }
        }
    }
}
