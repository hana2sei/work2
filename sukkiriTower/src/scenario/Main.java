package scenario;

import java.util.Scanner;

import character.Hero;

public class Main{
	public static void main(String[] args) {
		System.out.println("★☆スッキリタワー☆★");
		System.out.println("1:ゲームスタート");
		System.out.println("2:モンスターデータ取込（準備中）");

		int input = new Scanner(System.in).nextInt();

		switch (input) {
			case 1:
				Hero h = new Hero("ミナト", 400, 3, 30, 10, 50); // 勇者を生成
				Tower tower = new Tower(h);
				tower.explore();	// 探検開始
				break;

			case 2:
				System.out.println("この機能は準備中です。");
		}
	}
}
