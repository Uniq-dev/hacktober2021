def radix_sort(arr):
    """
    :param arr: list of integers
    :return: sorted list of integers
    """
    max_num = max(arr)
    exp = 1
    while max_num // exp > 0:
        buckets = [[] for _ in range(10)]
        for num in arr:
            buckets[(num // exp) % 10].append(num)
        arr = []
        for bucket in buckets:
            arr.extend(bucket)
        exp *= 10
    return arr


if __name__ == '__main__':
    arr = [170, 45, 75, 90, 802, 24, 2, 66]
    print(radix_sort(arr))
    arr = [170, 45, 75, 90, 802, 24, 2, 66, 1]
    print(radix_sort(arr))
    arr = [170, 45, 75, 90, 802, 24, 2, 66, 1, 0]
