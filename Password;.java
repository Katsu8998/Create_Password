package Password;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// パスワード作成
		int[] user_input = new int[4];
		Scanner scanner = new Scanner(System.in);// 文字考慮
		int counts = 0;
		System.out.println("パスワード作成フォーム");

		// ユーザ側に4つの数字を用意
		while (true) {
			System.out.println("パスワードに必要な4つの値を0-9の中から選んで下さい");
			try {
				for (int i = 0; i < 4; i++) {
					user_input[i] = new java.util.Scanner(System.in).nextInt();
				}
			} catch (Exception e) {
				System.out.println("数字以外の値が入力されました。4桁の数字を入力してください");
				continue;
			}

			// 重複
			for (int i = 0; i < user_input.length; i++)
				for (int j = 0; j < user_input.length; j++) {
					if (!(j == i) && user_input[i] == user_input[j]) {
						counts++;
					}
				}
			if (counts > 0) {
				System.out.println("重複箇所があります");
				counts = 0;
				continue;
			}
			// 10以上の数字確認
			int numUp = 0;
			for (int i = 0; i < user_input.length; i++) {
				if (user_input[i] >= 10) {
					numUp++;
				}
			}
			if (numUp > 0) {
				System.out.println("0-9の数字を入力して下さい");
				numUp = 0;
				continue;
			} else {
				System.out.println("");
			}
			break;
		}
		System.out.println("入力値");
		System.out.print(user_input[0]);
		System.out.print(user_input[1]);
		System.out.print(user_input[2]);
		System.out.print(user_input[3]);

		// ハッシュ化
		System.out.println("");
		System.out.println("もう一度4桁の数字を入力してください");
		String inputDigit = new java.util.Scanner(System.in).nextLine();// 文字列化変更
		String sha1 = "";
		String Hash = try1(inputDigit, sha1);

		//ログイン
		System.out.println("ログインしますか");
		System.out.println("1:はい 2:いいえ");
		int answer = new java.util.Scanner(System.in).nextInt();

		switch (1) {
		case 1:
			int login = 0;
			if (answer == 1) {
				while (login < 3) {
					System.out.println("パスワードを入力して下さい");
					String inputDigit2 = new java.util.Scanner(System.in).nextLine();

					//ここでもう一度ハッシュ化させる

					boolean logins = match(Hash, inputDigit2);
					if (!(logins)) {
						System.out.println("パスワードが正しくありません。");
						login++;
						continue;
					} else {
						System.out.println("ログイン成功");
						break;
					}
				}
				break;
			}
		default:
			System.out.println("ありがとうございました");
			break;
		}
	}

	public static boolean match(String Hash, String inputDigit2) {
		if (Hash.equals(inputDigit2)) {
			return true;
		}
		return false;

	}

	public static String try1(String inputDigit, String sha1) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] result = digest.digest(inputDigit.getBytes());
			sha1 = String.format("%040x", new BigInteger(1, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("新たなパスワードが生成されました。こちらのパスワードからログインできます。他の方に決して教えないで下さい。");
		System.out.println(sha1);
		return sha1;

	}

}
