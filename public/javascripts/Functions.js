//global variables

// click_list keeps track of the two cells that we need to find the shortest path between
let click_list = [];
// coordinate_list keeps track of the cells returned by the server call that need to be highlighted blue
let coordinate_list = [];


// Highlights a cell in the board based on its id and current color
// If the current color is blue it should go back to black or white, and if
// the current color is black or white, it should become blue
function highlight(click_id) {
    let class1 = document.getElementById(click_id).className;
    switch (class1) {
        // If the element is blue, change it back to its original color (black or white)
        case "grid-element-blue":
            let loc = click_id.split(',')
            class1 = (parseInt(loc[0]) + parseInt(loc[1])) % 2 == 0 ? "grid-element-white" : "grid-element-black";
            click_list = click_list.filter((value, index, arr) => value != click_id)
            break;

        // If the element is black or white, change it to blue
        case "grid-element-black":
        case "grid-element-white":
            class1 = "grid-element-blue";
            click_list.push(click_id);
            break;
    }
    document.getElementById(click_id).className = class1;
}


//Makes server call to root and provides the coordinates from click_list,
// server call takes it to the post method in index.js
function makeServerCall() {
    $.post("/", {A: click_list[0], B: click_list[1]})
        .done(function (data) {
            let json_data = JSON.parse(data);
            for (let i = 0; i < json_data.length; i++) {
                let coordinate = json_data[i].x + ',' + json_data[i].y;
                changeColor(coordinate);
            }
        });
}


//Changes the color of the given coordinate from white/black to blue
function changeColor(coordinates) {

    //Removing a class that doesn't exit does not throw an error so we can remove both black and white
    document.getElementById(coordinates).classList.remove("grid-element-white");
    document.getElementById(coordinates).classList.remove("grid-element-black");
    document.getElementById(coordinates).classList.add("grid-element-blue");
}





