/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function test(){
   return alert("test");
}

function svgDataURL(svg) {
    var svg_root = document.getElementById(svg);
    var svg_source = svg_root.outerHTML;
    var svgAsXML = (new XMLSerializer).serializeToString(svg_source);
    var svg_data_uri = 'data:image/svg+xml;base64,' + svgAsXML;
    return svg_data_uri;
}

function saveSvg(svgEl, name) {
    svgEl.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    var svgData = document.getElementById("TopDown").outerHTML;
    var preface = '<?xml version="1.1" standalone="no"?>\r\n';
    var svgBlob = new Blob([preface, svgData], {type:"image/svg+xml;charset=utf-8"});
    var svgUrl = URL.createObjectURL(svgBlob);
    var downloadLink = document.createElement("a");
    downloadLink.href = svgUrl;
    downloadLink.download = name;
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}


