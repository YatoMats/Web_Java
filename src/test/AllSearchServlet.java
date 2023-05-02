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
 * Servlet implementation class AllSearchServlet
 */
public class AllSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllSearchServlet() {
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

		//リクエストにエンコード指定
		request.setCharacterEncoding("UTF-8");

		String errMessage = "";
		String trs = "";
		//ArrayList<Integer> ids = new ArrayList<Integer>();
		//ArrayList<String> names = new ArrayList<String>();
		//ArrayList<Integer> balances = new ArrayList<Integer>();

		try {
			//データベースのドライバ読み込み
			Class.forName("com.mysql.jdbc.Driver");
			//データベースへ接続
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useSSL=false","root","root");
			//ステートメントの作成
			Statement state = connect.createStatement();

			//SQL文の送信、実行
			ResultSet result = state.executeQuery("select * from account");

			//受け取った結果から、各種処理
			while(result.next())
			{
				//ids.add(result.getInt("id"));
				//names.add(result.getString("name"));
				//balances.add(result.getInt("balance"));
				trs += "<tr><th>"+result.getInt("id")+"</th><td>"
						+result.getString("name")+"</td><td>"+result.getInt("balance")+"</td></tr>";
			}

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
			errMessage += "データベースエラー<br>";
		}

		//エラーがあるなら、
		if(!errMessage.equals(""))	//エラーメッセージが空でないなら、
		{
			//エラーメッセージをリクエストのアトリビュートに追加
			request.setAttribute("errMessage", errMessage);
			//データ検索画面へフォワード
			request.getRequestDispatcher("data_search.jsp").forward(request, response);
		}
		//エラーがないなら、
		else
		{
			//データをセッションのアトリビュートに追加
			HttpSession session = request.getSession();
			session.setAttribute("trs", trs);
			//全データ検索結果画面へフォワード
			request.getRequestDispatcher("all_search_result.jsp").forward(request, response);
		}
	}

}
