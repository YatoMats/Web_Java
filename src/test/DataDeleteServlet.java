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
 * Servlet implementation class DataDeleteServlet
 */
public class DataDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataDeleteServlet() {
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
		//リクエストデータをsessionから受け取る
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		//int id_int = Integer.parseInt(id);

		String errMessage = "";

		try {
			//データベースドライバーの読み込み
			Class.forName("com.mysql.jdbc.Driver");
			//データベースへ接続
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false","root","root");
			//ステートメントの準備
			Statement statement = connection.createStatement();

			//データベース内で該当データを削除(SQL文送信)
			statement.executeUpdate("DELETE FROM account WHERE ID=" + id);

			//データベースの切断
			statement.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			errMessage += "ドライバ読み込みエラー<br>";

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			errMessage += "データベースエラー<br>";
		}

		//エラーがあるなら、
		if(!errMessage.equals(""))
		{
			//エラー文をAttributeに設定
			request.setAttribute("errMessage",errMessage);
			//検索結果ページへフォワード
			request.getRequestDispatcher("data_search.jsp").forward(request, response);
		}
		//エラーがないなら、
		else
		{
			//削除完了画面へフォワード
			request.getRequestDispatcher("delete_result.jsp").forward(request, response);
		}
	}

}
