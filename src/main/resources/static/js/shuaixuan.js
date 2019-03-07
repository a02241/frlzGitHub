function test(str){
var str = str;
    var regUpper = /[A-Z]/;
    var regLower = /[a-z]/;
    var regStr = /[0-9]/;
    var complex = 0;
    if (regLower.test(str)) {
    ++complex;
    }
    if (regUpper.test(str)) {
    ++complex;
    }
    if(regStr.test(str)){
    ++complex;
    }
    if ( str.length >= 8 && complex>=2) {
    return(1)
    }else{
    return(2)
    }
}
test('12314')
    