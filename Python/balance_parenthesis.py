def check(string_in):
    brackets = ['()', '{}', '[]']
    while any(x in string_in for x in brackets):
        for br in brackets:
            string_in = string_in.replace(br, '')
    return not string_in
    
string = "{}}}}"
print(string, "-", "Balance" 
      if check(string) else "Unbalance")