# Web_Java
JavaとMySQLを使った動的Webプロジェクト。  

開発環境は Pleiades Eclipse 

実行確認した仮想サーバーは Apache Tomcat v9.0 

MySQL上に銀行口座を想定した簡易的なデータテーブルを作成。

## データテーブル定義  
  テーブル名 : Account ;  
   カラム定義 :  
      ID : ;  
      Name : 名前 ;  
      Balance : 残高;  
   ;  

jspファイル上で、データの検索、全表示、検索したデータの削除などの、各操作を行う。

サーブレット上でMysSQLと接続、データテーブルにアクセスし、各種操作を行う。
