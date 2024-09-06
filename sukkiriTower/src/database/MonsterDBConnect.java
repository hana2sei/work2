package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import character.Monster;

/*
 * 「モンスター」テーブルへのアクセス
 * を行うクラスです
 */
public class MonsterDBConnect {
	private final String URL = "jdbc:postgresql://localhost:5432/rpg";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

	// コンストラクタ
	public MonsterDBConnect() {
        /* JDBCドライバの準備 */
    	try {
    		Class.forName("org.postgresql.Driver");
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    /*
     * 「モンスター」テーブルから、渡されたモンスターNoに該当するモンスターデータを検索します。
     */
    public Monster select(int no) {
    	Monster m = null;

        /* 1) SQL文の準備 */
		String sql = "";
		sql = "SELECT * FROM monster ";
		sql += "WHERE monster_no = ?;";

        /* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の?部分を置き換え */
			st.setInt(1, no);

			/* 4) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 5) 検索結果のモンスターデータよりMonsterインスタンスを作成 */
			if (rs.next()) {
				m = new Monster(rs.getString("name"),
								rs.getInt("hp"),
								rs.getInt("mp"),
								rs.getInt("power"),
								rs.getInt("defense"),
								rs.getInt("special_power"));
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return m;
    }
}