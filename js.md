```
function changeData(data){
	if(data.treeData.children !=null && data.treeData.children !='undefined' && data.treeData.children.length !=0 ){
		for(var i=0; i<data.treeData.children.length;i++){
            changeColor(data.treeData.children[i].status,data.treeData.children,i)
            child(data.treeData.children[i].status,data.treeData.children[i].children)
		}
	}
}


function child(status,params){
    if(params!=null){
		for(var j=0;j<params.length;j++){
			if(params[j].children!=null){
				child(params[j].status,params[j].children)
			}
			changeColor(params[j].status,params,j);
		}
    }
}


function changeColor(obj,dd,k){
	if(obj=='1'){
		dd[k].itemStyle={color:'red'}
	}
	...
}


```

