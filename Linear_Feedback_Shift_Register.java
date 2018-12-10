/* *****************************************************************************
 * Amir Mokhammed-Ali
 * Данный код предоставляет API для симуляции регистра сдвига с линейной обратной связи,
 * путем создания псевдо-рандомного генератора и произведения сдвигов на любое количество шагов.
 * Подробнее о РСЛОС: https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D0%B3%D0%B8%D1%81%D1%82%D1%80_%D1%81%D0%B4%D0%B2%D0%B8%D0%B3%D0%B0_%D1%81_%D0%BB%D0%B8%D0%BD%D0%B5%D0%B9%D0%BD%D0%BE%D0%B9_%D0%BE%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%BE%D0%B9_%D1%81%D0%B2%D1%8F%D0%B7%D1%8C%D1%8E
 **************************************************************************** */

public class LFSR {

    private String Seed; // attribute of the class that saves the given seed
    private int Tap; // attribute of the class that saves the given tap

    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap) {
        // PUT YOUR CODE HERE
        this.Tap = tap;
        this.Seed = seed;
    }

    // returns the number of bits in this LFSR
    public int length() {
        // PUT YOUR CODE HERE
        return this.Seed.length();
    }

    // returns the ith bit of this LFSR (as 0 or 1)
    public int bitAt(int i) {
        // PUT YOUR CODE HERE
        int len = length();
        char x = this.Seed.charAt(len - i);
        if (x == '0') return 0;
        return 1;
    }

    // returns a string representation of this LFSR
    public String toString() {
        // PUT YOUR CODE HERE
        return this.Seed;
    }

    // simulates one step of this LFSR and returns the new bit (as 0 or 1)
    public int step() {
        // PUT YOUR CODE HERE
        int bit1 = bitAt(length());
        int bitTap = bitAt(this.Tap);
        int xor = bit1 ^ bitTap;
        int len = length();
        this.Seed = this.Seed.substring(1) + xor;
        return xor;

    }

    // simulates k steps of this LFSR and returns the k bits as a k-bit integer
    public int generate(int k) {
        // PUT YOUR CODE HERE
        int ans = 0;
        for (int i = k - 1; i >= 0; --i) {
            ans += Math.pow(2, i) * step();
        }
        return ans;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        // PUT YOUR TEST CODE HERE
        LFSR lfsr1 = new LFSR("01101000010", 9);

        StdOut.println(lfsr1);

        for (int i = 0; i < 10; i++) {
            int r = lfsr1.generate(5);
            StdOut.println(lfsr1 + " " + r);
        }
    }

}
