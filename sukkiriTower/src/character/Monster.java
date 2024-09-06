package character;

import java.util.Random;

/*
 * モンスターを表すクラス
 */
public class Monster extends Character {

	// コンストラクタ
	public Monster(String name, int hp, int mp, int power, int defense, int specialPower) {
		super(name, hp, mp, power, defense, specialPower);
	}

	/*
	 * モンスターに行動させる。
	 * 行動内容は以下の確率でランダムに決定する。
	 * （戦う：75％　必殺技：15％　様子をみる：10％）
	 */
	public void action(Hero h) {
		int val = new Random().nextInt(100) + 1;	// 1～100の乱数を生成

		if (val <= 75) {
			attack(h);			// 攻撃
		} else if (val <= 90) {
			specialAttack(h);	// 必殺技
		} else {
			waitAndSee();		// 様子をみる
		}
	}

	/*
	 * 様子をみる
	 */
	public void waitAndSee() {
		System.out.println(getName() + "は様子をみている");
	}
}
