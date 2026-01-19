# 📊 C Data Types Range Chart

This table summarizes the size, format specifier, and value range for common data types in C.

| Data Type                | Size (Bytes)            | Format Specifier | Min Value                         | Max Value                          |
|--------------------------|--------------------------|------------------|-----------------------------------|------------------------------------|
| **`char`**               | 1                        | `%c`             | -128                              | 127                                |
| **`unsigned char`**      | 1                        | `%c`             | 0                                 | 255                                |
| **`short int`**          | 2                        | `%hd`            | -32,768                           | 32,767                             |
| **`unsigned short`**     | 2                        | `%hu`            | 0                                 | 65,535                             |
| **`int`**                | 4                        | `%d`             | -2,147,483,648                    | 2,147,483,647                      |
| **`unsigned int`**       | 4                        | `%u`             | 0                                 | 4,294,967,295                      |
| **`long int`**           | 4 (32-bit) / 8 (64-bit)  | `%ld`            | -2,147,483,648 (32-bit) / −9.2e18 | 2,147,483,647 (32-bit) / 9.2e18    |
| **`unsigned long`**      | 4 or 8                   | `%lu`            | 0                                 | 4.29e9 (32-bit) / 1.84e19 (64-bit) |
| **`long long int`**      | 8                        | `%lld`           | -9,223,372,036,854,775,808        | 9,223,372,036,854,775,807          |
| **`unsigned long long`** | 8                        | `%llu`           | 0                                 | 18,446,744,073,709,551,615         |
| **`float`**              | 4                        | `%f`             | ~1.2 × 10⁻³⁸                      | ~3.4 × 10³⁸                        |
| **`double`**             | 8                        | `%lf`            | ~2.2 × 10⁻³⁰⁸                     | ~1.8 × 10³⁰⁸                       |
| **`long double`**        | 10/12/16                 | `%Lf`            | ~3.4 × 10⁻⁴⁹³²                    | ~1.1 × 10⁴⁹³²                      |

> ⚠️ Note: Actual sizes and ranges may vary slightly depending on the compiler and system architecture.
> You can check your system-specific values using `<limits.h>` and `<float.h>`.




# ⚙️ C Operator Precedence and Associativity Chart

Operator precedence determines the order in which operators are evaluated in expressions. Operators with higher precedence are evaluated before operators with lower precedence.

| Precedence Level | Operators                                  | Description                                 | Associativity        |
|------------------|--------------------------------------------|---------------------------------------------|----------------------|
| 1 (Highest)      | `()` `[]` `->` `.`                         | Function call, array subscript, struct access | Left to Right        |
| 2                | `++` `--` `+` `-` `!` `~` `*` `&` `sizeof`  | Unary plus/minus, logical NOT, bitwise NOT, dereference, address-of, sizeof | Right to Left        |
| 3                | `*` `/` `%`                                | Multiplication, division, modulo            | Left to Right        |
| 4                | `+` `-`                                    | Addition, subtraction                       | Left to Right        |
| 5                | `<<` `>>`                                  | Bitwise shift left and right                | Left to Right        |
| 6                | `<` `<=` `>` `>=`                          | Relational operators                        | Left to Right        |
| 7                | `==` `!=`                                  | Equality operators                          | Left to Right        |
| 8                | `&`                                        | Bitwise AND                                 | Left to Right        |
| 9                | `^`                                        | Bitwise XOR                                 | Left to Right        |
| 10               | `\|`                                       | Bitwise OR                                  | Left to Right        |
| 11               | `&&`                                       | Logical AND                                 | Left to Right        |
| 12               | `\|\|`                                     | Logical OR                                  | Left to Right        |
| 13               | `?:`                                       | Ternary conditional                         | Right to Left        |
| 14               | `=` `+=` `-=` `*=` `/=` `%=` `<<=` `>>=` `&=` `^=` `|=` | Assignment operators                       | Right to Left        |
| 15 (Lowest)      | `,`                                        | Comma operator                              | Left to Right        |

> ⚠️ Note:
> - Higher number means **lower precedence**.
> - Parentheses `()` can always be used to **override default precedence**.
> - `sizeof`, `typecast`, and `conditional` (`?:`) are often sources of confusion — double-check their usage in complex expressions.

📚 Format Specifier Cheat Sheet
| Type                 | Format Specifier |
| -------------------- | ---------------- |
| `int`                | `%d`             |
| `unsigned int`       | `%u`             |
| `short int`          | `%hd`            |
| `unsigned short`     | `%hu`            |
| `long int`           | `%ld`            |
| `unsigned long`      | `%lu`            |
| `long long int`      | `%lld`           |
| `unsigned long long` | `%llu`           |
| `float`              | `%f`             |
| `double`             | `%lf`            |
| `long double`        | `%Lf`            |
| `char`               | `%c`             |
| `string` (`char*`)   | `%s`             |
| `pointer`            | `%p`             |


🔢 Bitwise Operators Summary
| Operator | Meaning     | Use Case                                   |              |
| -------- | ----------- | ------------------------------------------ | ------------ |
| `&`      | AND         | Masking bits                               |              |
| \`       | \`          | OR                                         | Setting bits |
| `^`      | XOR         | Toggle bits                                |              |
| `~`      | NOT         | Invert all bits                            |              |
| `<<`     | Left Shift  | Multiply by powers of 2                    |              |
| `>>`     | Right Shift | Divide by powers of 2 (signed shifts vary) |              |


🔄 Memory Allocation Sizes
| Keyword            | Memory Area  | Example Use       |
| ------------------ | ------------ | ----------------- |
| `int a = 5;`       | Stack        | Automatic storage |
| `static int x;`    | Data         | Global/static     |
| `malloc()`         | Heap         | Dynamic memory    |
| `char *str = "hi"` | Text/RO Data | String literals   |
static void pattern25(String S){
    int n = S.length();

    System.out.println("\nPattern 25");

    int left = 0;
    int right = n - 1;
    boolean flag = false;

    for(int i = 0 ; i < n ;i++){

        for(int j = 0; j < left; j++)
        {
            System.out.print(" ");
        }

        System.out.print(S.charAt(left));

        for(int j = 0; j < right - left - 1; j++)
        {
            System.out.print(" ");
        }

        if(left != right){
            System.out.print(S.charAt(right));
        }

        System.out.println();

        if(left == right)
        {
            flag = true;
        }

        if(!flag)
        {
            left++;
            right--;
        }
        else
        {
            left--;
            right++;
        }
    }
}
