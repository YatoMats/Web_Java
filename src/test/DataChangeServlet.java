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
 * Servlet implementation class DataChangeServlet
 */
public class DataChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataChangeServlet() {
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
		//※リクエストデータの取得
		//エンコード設定
		request.setCharacterEncoding("UTF-8");
		//request から、パラメータの取得
		String name = (String)request.getParameter("name");
		String balance = (String)request.getParameter("balance");
		//request から、 session の取得
		HttpSession session = request.getSession();
		//session から、Attribute データの取得
		String id = (String)session.getAttribute("id");

		//入力データのチェック
		String errMessage = "";
		//名前エラーチェック
		if((name == null) || (name.equals("")) || (name.matches("[ 　]*")))
		{
			errMessage += "名前を正しく入力してください。<br>";
		}
		//残高エラーチェック
		if(!balance.matches("[0-9]+"))
		{
			errMessage += "残高を半角数字で入力してください。<br>";
		}

		if(errMessage.equals(""))	//入力エラーの確認(入力エラーがないなら、)
		{
			try {
				//ドライバの読み込み
				Class.forName("com.mysql.jdbc.Driver");
				//データベースへ接続
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false","root","root");
				//Statementを準備
				Statement state = connect.createStatement();

				//SQL文送信、命令の実行
				state.executeUpdate("UPDATE account SET name='" + name +  "',balance=" + balance + " WHERE id=" + id);

				//データベース切断
				state.close();
				connect.close();

			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				errMessage += "ドライバ読み込みエラー<br>";

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				errMessage += "データベースエラー<br>";
			}
		}

		//更新データを改めてセッションのAttributeに設定
		session.setAttribute("name", name);
		session.setAttribute("balance", balance);

		//エラーがあるなら、
		if(!errMessage.equals(""))	//エラーメッセージ変数が空でないなら、
		{	//エラーメッセージをリクエストのAttributeに追加。
			request.setAttribute("errMessage", errMessage);
			//データ変更画面にフォワード
			request.getRequestDispatcher("data_change.jsp").forward(request, response);
		}
		//エラーがなければ、
		else
		{	//データ変更完了画面にフォワード
			request.getRequestDispatcher("change_result.jsp").forward(request, response);
		}
	}

}
