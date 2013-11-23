package cryptography;

import java.math.BigInteger;
import java.util.Random;

public class DiffieHellmanMPK {
	private static Random randomGenerator = new Random();
	private static long pValue = randomGenerator.nextInt(1000000);
	private static long gValue = randomGenerator.nextInt(100000);
	private static boolean isWorked = true;
	private static boolean isInited = false;
	private static boolean isInitedKlient = false;
	private static boolean isInitedServer = false;
	private static BigInteger p;
	private static BigInteger g;
	private static BigInteger openClient;
	private static BigInteger openServer;
	private static BigInteger powerOfClient;
	private static BigInteger powerOfServer;

	private static void initianilizatoin() {
		int iterations = 0;
		while (iterations < 1000) {
			iterations++;
			long pValue1 = randomGenerator.nextInt(1000000);
			// System.out.println("temp "+pValue1);
			if (isPrime(pValue1)) {
				// System.out.println("primary temp "+pValue1);
				long pValue2 = (long) ((pValue1 - 1) * 0.5);
				// System.out.println("half temp  "+pValue2);
				if (isPrime(pValue2)) {
					System.out.println("primary temp " + pValue1);
					System.out.println("primary half temp  " + pValue2);
					pValue = pValue1;
					System.out.println("Iterations: " + iterations);
					iterations = 0;
					break;
				}
				;
			}
			;
			if (iterations > 998) {
				isWorked = false;
				System.out.println("Life pain - you unical" + iterations
						+ "bye,bye");
			}
		}
		while (gValue > pValue) {
			long gValue2 = randomGenerator.nextInt(100000);
			if (gValue2 < pValue) {
				gValue = gValue2;
				break;
			}
		}
		p = BigInteger.valueOf(pValue);
		g = BigInteger.valueOf(gValue);

		return;
	}

	private static boolean isPrime(long n) {
		if (n % 2 == 0) {
			return false;
		}

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void initKlient(long intitPower) {
		if (!isInited) {
			isInited = true;
			initianilizatoin();
		}
		if (isWorked) {
			powerOfClient = BigInteger.valueOf(intitPower);
			isInitedKlient = true;
			openClient = g.modPow(powerOfClient, p);
		}
		return;
	}

	public static void initServer(long intitPower) {
		if (!isInited) {
			isInited = true;
			initianilizatoin();
		}
		if (isWorked) {
			isInitedServer = true;
			powerOfServer = BigInteger.valueOf(intitPower);
			openServer = g.modPow(powerOfServer, p);
		}
		return;
	}

	public static BigInteger getKeyKlient(long intitPower) {
		if (!isInitedKlient) {
			initKlient(intitPower);
		}
		if (powerOfClient.equals(BigInteger.valueOf(intitPower)))

		{
			if (isInitedServer)
				return openServer.modPow(BigInteger.valueOf(intitPower), p);
			else {
				System.err
						.println("Дані не проініцілювазовані протилежною стороною(server)");
				return BigInteger.valueOf(0);
			}
		} else
			System.err.println("Введено різні степені ");
		return BigInteger.valueOf(0);
	}

	public static BigInteger getKeyServer(long intitPower) {
		if (!isInitedServer) {
			initServer(intitPower);
		}
		if (powerOfServer.equals( BigInteger.valueOf(intitPower)))

		{
			if (isInitedKlient)
				return openClient.modPow(BigInteger.valueOf(intitPower), p);
			else {
				System.err
						.println("Дані не проініцілювазовані протилежною стороною(client)");
				return BigInteger.valueOf(0);
			}
		} else
			System.err.println("Введено різні степені ");
		return BigInteger.valueOf(0);
	}

//	public static void main(String[] args) {
//		Random randomGenerator = new Random();
//		long Alise = randomGenerator.nextInt(1000000);
//		long Ben = randomGenerator.nextInt(1000000);
//		System.out.println(Alise);
//		System.out.println(Ben);
//		initKlient(Alise);
//		initServer(Ben);
//		System.out.println(getKeyKlient(Alise));
//		System.out.println(getKeyServer(Ben));
//		
//		
//		// Початок костиля
////		String result = closeBen.toString();
////		for (int i = result.length(); i < 16; i++) {
////			result += "7";
////		}
////		// кінець костиля
//		
//
//
//	}

}
