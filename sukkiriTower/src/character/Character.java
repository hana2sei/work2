package character;

/*
 * ゲーム内の登場人物を表す抽象クラス
 */
public abstract class Character {
	private String name;		// 名前
	private int hp;				// HP
	private int mp;				// MP（必殺技を使える残り回数）
	private int power;			// 攻撃力
	private int defense;		// 防御力
	private int specialPower;	// 必殺技威力

	// コンストラクタ
	public Character(String name, int hp, int mp, int power, int defense, int specialPower) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.power = power;
		this.defense = defense;
		this.specialPower = specialPower;
	}

	/*
	 * 攻撃
	 */
	public void attack(Character c) {
		System.out.println(getName() + "の攻撃！");

		// ダメージ処理
		// （ダメージ値 = 自分の攻撃力 - 相手の防御力。
		//   ただしダメージがマイナスになる場合は、ダメージ値は0として扱う）
		int damage = getPower() - c.getDefense();
		if (damage < 0) {
			damage = 0;
		}
		c.setHp(c.getHp() - damage);

		System.out.println(c.getName() + "に" + damage + "のダメージ！！");
	}

	/*
	 * 必殺技による攻撃
	 */
	public void specialAttack(Character c) {
		System.out.println(getName() + "は必殺技を放った！！");

		// MPの残量チェック（MPがなければ攻撃は失敗する）
		if (getMp() > 0) {
			setMp(getMp() - 1);	// MPの減少処理

			// ダメージ処理
			// (必殺技の場合は、必殺技威力がそのままダメージとなる）
			int damage = getSpecialPower();
			c.setHp(c.getHp() - damage);

			System.out.println(c.getName() + "に" + damage + "のダメージ！！");
		} else {
			System.out.println("しかし技は失敗した…");
		}
	}

	/*
	 * 死亡しているかのチェック
	 * このメソッドは以下の戻り値を返します。
	 * true:	死亡
	 * false:	生存
	 */
	public boolean checkDie() {
		if (getHp() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// ゲッター＆セッター
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		// 設定されるHPがマイナスの値の場合は「0」を設定
		if (hp < 0) {
			this.hp = 0;
		} else {
			this.hp = hp;
		}
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpecialPower() {
		return specialPower;
	}

	public void setSpecialPower(int specialPower) {
		this.specialPower = specialPower;
	}
}
