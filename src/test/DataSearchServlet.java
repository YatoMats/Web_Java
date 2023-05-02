package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DataSearchServlet
 */
public class DataSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataSearchServlet() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);

		String name = null;
		int balance = 0;
		String errMessage = "";

		//パラメータにエンコード指定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得
		String id = request.getParameter("id");
		//String pass = request.getParameter("pass");

		//String truePass = "myPass00";

		//入力データのチェック
		//データが未入力なら、
		if(id == null || id.equals("") || id.matches("[ 　]*"/*半角か全角スペースの連続*/))
		{
			errMessage += "id番号を入力してください。<br>";
		}
		if(!id.matches("[0-9]*"/*0から9の数値の連続*/))
		{
			errMessage += "半角数字で入力してください。<br>";
		}
		/*
		//パスワード確認
		if(!pass.equals(truePass))	//パスワードが一致しないなら、
		{
			errMessage += "パスワードが一致しません<br>";
		}*/

		//データベースの検索
		if(errMessage.equals(""))
		{
			try {
				//データベースドライバのロード
				Class.forName("com.mysql.jdbc.Driver");
				//データベースへの接続(MySQLへのログイン）
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false","root","root");
				//SQLコンテナの作成
				Statement statement = connect.createStatement();

				//SQL文の送信
				ResultSet result = statement.executeQuery("select * from account where id=" + id);

				//結果からデータ取り出し
				if(result.next())
				{
					name = result.getString("name");
					balance = result.getInt("balance");
				}
				else
				{
					errMessage += "指定したID番号が間違っています。<br>";
				}

				//データベースの切断
				statement.close();
				connect.close();

			} catch (ClassNotFoundException e) {
				errMessage += "データベースドライバが見つかりません。<br>";
				e.printStackTrace();
			} catch (SQLException e) {
				errMessage += "データベースエラー<br>";
				e.printStackTrace();
			}
		}

		//エラーがあれば入力画面にフォワード
		if(!errMessage.equals(""))
		{
			request.setAttribute("errMessage", errMessage);
			request.getRequestDispatcher("data_search.jsp").forward(request, response);
		}
		//なければ検索結果画面にフォワード
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("balance", balance);
			request.getRequestDispatcher("search_result.jsp").forward(request, response);
		}
	}

}
