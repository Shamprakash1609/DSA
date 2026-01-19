n = int(input())

nums = list(map(int, input().split()))

xor = 0

# XOR of 1..n
for i in range(1, n + 1):
    xor ^= i

# XOR of given numbers
for x in nums:
    xor ^= x

print(xor)
