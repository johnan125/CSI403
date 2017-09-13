function main(){
    var type = "quicksort"
    var testArray = [5,7,5,3,5,6,7,2];
    var insertedArray = testArray;
    var object = {inList: insertedArray,
                    outList: null,
                    algorithm: null,
                    timeMS: null

                }
    object.inList = insertedArray;
    console.log(object.inList);
    object.algorithm = type;

    
    if(testArray instanceof Array){
        var a = performance.now();
        quickSort(testArray,0, testArray.length-1);
        var b = performance.now();
        var ms = (b-a).toFixed(2);
        console.log(ms);
        object.timeMS = ms;
        object.outList = testArray;
    }
    else{
        return console.log("Malformed JSON");
    }

    console.log(object.inList);

    
}

function quickSort(array,start,end){
    var type = "quicksort"
    var pIndex;

    if(start < end){
        pIndex = partition(array, start, end);
        quickSort(array, start, pIndex - 1);
        quickSort(array, pIndex + 1, end);
    }
    
    
}

function partition(array, start, end){
    var x = end;
    var i = start - 1;
    var temp;

    for(var j = start; j <= end-1; j++){
        if(array[j] <= array[x]){
            i++;
            temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            temp = 0;
            
            
        }
    }

    temp = array[i+1];
    array[i+1] = array[x];
    array[end] = temp;
    temp = 0;

    return i+1;
}

main();