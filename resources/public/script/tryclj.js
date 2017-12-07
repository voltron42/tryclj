function tryclj() {
    var out = document.getElementById("out");
    var input = document.getElementById("in").value;
    out.innerHTML = out.innerHTML + "<span class=\"input\">" + input + "</span>";
    out.scrollTop = out.scrollHeight;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.response);
            var result = JSON.parse(this.response);
            var output = "";
            if (result.error) {
                output += "<span class=\"error\">" + result.error.message + "</span>"
            }
            if (result.console && result.console[0] && (result.console.length > 1 || result.console[0] != "")) {
                result.console.forEach(function(line) {
                    output += "<span class=\"console\">" + line + "</span>"
                })
            }
            if (result.result) {
                output += "<span class=\"result\">=&gt; " + result.result + "</span>"
            }
            out.innerHTML = out.innerHTML + output;
            out.scrollTop = out.scrollHeight;
        }
    };
    xhttp.open("post", "repl", true);
    xhttp.send(input);
}