package scenario;

import java.util.Scanner;

import character.Hero;
import character.Monster;
import database.MonsterDBConnect;

/*
 * スッキリタワー クラス
 */
public class Tower {
	private int floorNo = 1;	// 現在の階層
	private Hero h;				// 塔の中にいる勇者

	// コンストラクタ
	public Tower(Hero h) {
		this.h = h;
	}

	/*
	 * 塔を探検する
	 */
	public void explore() {
		MonsterDBConnect db = new MonsterDBConnect();

		while (true) {
			Monster m = db.select(this.floorNo);	// 階層に対応したモンスターデータを取得

			// モンスターが見つからない場合、探検終了（ゲームクリア）
			if (m == null) {
				System.out.println("おめでとう！クリアです！！");
				System.out.println("--E N D--");
				break;
			}

			System.out.println("【" + this.floorNo + "階に到着した】");

			// 戦闘を行う
			if (!battle(m)) {
				// 敗北した場合、探検終了（ゲームオーバー）
				System.out.println(this.h.getName() + "は死んでしまった‥");
				System.out.println("--GAME OVER--");
				break;
			}

			this.floorNo++;			// 次の階に進む
			System.out.println();	// 改行処理
		}
	}

	/*
	 * 戦闘を行う
	 * このメソッドは以下の戻り値を返します。
	 * true:	勇者の勝利（モンスターのHPが0、または逃走成功）
	 * false:	モンスターの勝利（勇者のHPが0）
	 */
	public boolean battle(Monster m) {
		// モンスターの出現メッセージを表示
		System.out.println(m.getName() + "があられた！！");

		// 戦闘終了時はreturnでメソッドを終了するため、while文の条件はtrueを指定し無限ループさせる。
		while (true) {
			// 勇者のHP、MPを画面に表示
			this.h.showStatus();

			// 勇者の行動
			System.out.print("コマンドを入力（1:攻撃 2:必殺技 3:逃げる) >>");
			int input = new Scanner(System.in).nextInt();

			switch (input) {
				case 1:
					// 戦うを選択
					this.h.attack(m);
					break;
				case 2:
					// 必殺技を選択
					this.h.specialAttack(m);
					break;
				case 3:
					// 逃げるを選択
					if (this.h.escape()) {
						// 逃げるのに成功すれば、戦闘終了とする。
						return true;
					}
			}

			// モンスターを倒したのかを判定
			if (m.checkDie()) {
				System.out.println(m.getName() + "をやっつけた！");
				return true;
			} else {
				// モンスターの行動
				m.action(this.h);
			}

			// 勇者の生存判定（死亡して入れば戦闘終了）
			if (this.h.checkDie()) {
				return false;
			}
		}
	}
}
