var express = require("express"),
    app = express(),
    bodyParser = require("body-parser");

app.use(bodyParser.urlencoded({extended:true}));
app.set("viewengine", "ejs");

app.get("/", function(req, res){

    res.render("landing");
    
});

app.post("/", function(req, res){

//

});


app.listen(3000, function(){
    console.log("server has started");
});