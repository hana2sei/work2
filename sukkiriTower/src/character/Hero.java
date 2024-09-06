package character;

import java.util.Random;

/*
 * 勇者を表すクラス
 */
public class Hero extends Character {

	// コンストラクタ
	public Hero(String name, int hp, int mp, int power, int defense, int specialPower) {
		super(name, hp, mp, power, defense, specialPower);
	}

	/*
	 * 逃げる。成功率は20％
	 * このメソッドは以下の戻り値を返します。
	 * true:	逃走成功
	 * false:	逃走失敗
	 */
	public boolean escape() {
		System.out.println(getName() + "は逃げ出した！");

		int val = new Random().nextInt(100) + 1;	// 1～100の乱数を生成

		// 20以下の値が選ばれたときのみ逃走成功とする（20％で成功）
		if (val <= 20) {
			System.out.println(getName() + "はうまく逃げ切れた");
			return true;
		} else {
			System.out.println(getName() + "は逃げ切れなかった");
			return false;
		}
	}

	/*
	 * ステータス表示
	 */
	public void showStatus() {
		System.out.println("[ HP：" + getHp() + " MP：" + getMp() + " ]--------------------------------------------");
	}
}
