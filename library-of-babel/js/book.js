var CHANCE_OF_SENSIBLE_TEXT = 0.5; // MUCH better odds than the Library in the story

var SYMBOLS = ['a','b','c','d','e','f','g','h','i',
	       'j','k','l','m','n','o','p','q','r','s','t',
	       'u','v','w','x','y','z',' ','.',','];

var PAGES_PER_BOOK = 410;
var LINES_PER_PAGE = 40;
var SYMBOLS_PER_LINE = 80;

var PREMADE_TEXTS = ['texts/hamlet.txt',
		     'texts/baudelaire.txt',
		     'texts/flatland.txt',
		     'texts/analects.txt',
		     'texts/mvc.txt',
		     'texts/relativity.txt',
		     'texts/spinoza.txt',
		     'texts/macbeth.txt',
		     'texts/pulp_fiction.txt',
		     'texts/maad_city.txt'];

function Chance(){
    var chance = Math.random();
    return (chance < CHANCE_OF_SENSIBLE_TEXT);
}

function getRandomBook(){
    // returns a randomly-generated book
    var book = [];
    for (var i = 0; i < PAGES_PER_BOOK; i++){
	var page = [];
	for (var j = 0; j < LINES_PER_PAGE; j++){
	    var line = "";
	    while (line.length < SYMBOLS_PER_LINE){
		// append a new random symobl to the line
		var n = Math.floor(Math.random() * SYMBOLS.length);
		line = String.concat(line, SYMBOLS[n]);
	    }
	    page.push(line);
	}
	book.push(page);
    }
    return book;
}

function getPremadeBook(){
    // load a text from the texts folder and turn it into a book array
    var file = PREMADE_TEXTS[Math.floor(Math.random() * PREMADE_TEXTS.length)];
    var request = $.ajax({
	url: file,
	async: false,
    });
    var text = request.responseText.split("\n"); //text.split("\n");
    var book = [];
    for (var i = 0; i < text.length; i += 40){
	var page = text.slice(i, i + 40);
	book.push(page);
    }
    return book;
}

function getBook(){
    var book;    
    if (Chance()){
	book = getPremadeBook();
    } else {
	book = getRandomBook();
    }
    return book;
}	

function printPage(page, output_id){
    $("#" + output_id).html("");
    var text = $("#" + output_id).html();
    for (x in page){
	text = String.concat(text, page[x] + "<br>");
    }
    $("#" + output_id).html(text + '<br>' + (current_page + 1));
}

var book = getBook();
var current_page = 0;
printPage(book[current_page], "output1");
printPage(book[++current_page], "output2");
