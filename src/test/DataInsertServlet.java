package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DataInsertServlet
 */
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//※リクエストデータの受け取り
		//リクエストデータへのエンコード設定
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの取得
		String id = (String)request.getParameter("id");
		String name = (String)request.getParameter("name");
		String balance = (String)request.getParameter("balance");

		String errMessage = "";

		//※入力データの確認
		//IDの確認
		if(!id.matches("[0-9]+"))	//半角数字の連続データでないなら、エラー
		{
			errMessage += "IDを半角数字で入力してください。<br>";
		}
		//名前の確認
		if(name == null || name.equals("") || name.matches("[ 　]*"))	//データが空か、スペースのみなら、エラー
		{
			errMessage += "名前を入力してください。<br>";
		}
		//残高の確認
		if(!balance.matches("[0-9]+"))	//半角数字の連続データでないなら、エラー
		{
			errMessage += "残高を半角数字で入力してください。<br>";
		}

		//入力エラーがないなら、
		if(errMessage.equals(""))
		{
			try {
				//ドライバ読み込み
				Class.forName("com.mysql.jdbc.Driver");
				//データベースへ接続
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false","root","root");
				//ステートメントの作成
				Statement state = connect.createStatement();

				//SQL文送信、実行
				state.executeUpdate("insert into account(id,name,balance) values("+id+",'"+name+"',"+balance+")");
				//state.executeUpdate("insert into account(id,name,balance) values(99,'Boo',12345)");

				//データベースからの切断
				state.close();
				connect.close();

			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				errMessage += "ドライバ読み込みエラー<br>";

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				//errMessage += e.getMessage();
				errMessage += "データベースエラー<br>";
			}
		}

		//新規データをセッションのAttributeに設定
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("balance", balance);

		//エラーがあれば（エラー変数が空でないなら）、
		if(!errMessage.equals(""))
		{
			//エラーメッセージをリクエスト・アトリビュートに設定
			request.setAttribute("errMessage", errMessage);
			//データ挿入ページへフォワード
			request.getRequestDispatcher("data_insert.jsp").forward(request, response);
		}
		//エラーがないなら、
		else
		{
			//データ検索ページへフォワード
			request.getRequestDispatcher("insert_result.jsp").forward(request, response);
		}
	}

}
