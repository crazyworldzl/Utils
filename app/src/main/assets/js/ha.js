/**
 * Created by Administrator on 2017/10/9.
 */
document.writeln("<link rel='stylesheet' href='../css/span.css'> ")
for (var i = 0; i < 10; i++) {
    var b = "";
    for (var d = 0; d < i; d++) {
        b += "哈";
    }
    document.writeln("<a onclick='aa()'  >" + b + i + "</a><br/><hr/>")
}
function aa() {
    document.writeln("<span>made</span>")
}
