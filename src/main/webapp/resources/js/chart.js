var period;
var term;
var segment;

$(function () {

	// chart theme
	Highcharts.theme = {
		colors: ['#0079c1', '#a9cc5b', '#e4582b', '#f8c624', '#49bb93', '#b457c2', '#ef4c82', '#a6b9bc', '#a9cc5b', '#3ac6e2', '#6060e8', '#ef6f6f' ]
		//original
		//colors: ['#20419A', '#E40375', '#F36C21', '#EEE824', '#75C044', '#00B05A', '#44C8F5', '#7F3F98', '#72808A']
		,chart: {
			backgroundColor: '#ffffff'
		}
	};

	// apply the theme
	var highchartsOptions = Highcharts.setOptions(Highcharts.theme);
});

function makeCombinationChart(chart, renderTo, title, subtitle,cate,seriesname,pieToolTip,pieTitle,serise){

	chart = new Highcharts.Chart({
		 chart: {
			 renderTo: renderTo
	     },
         title: {
             text: title
         },
		 subtitle: {
             text: subtitle
         },
         xAxis: {
             categories: cate
         },
		 yAxis: {
             title: {
                 text: seriesname
             }
         },
         tooltip: {
             formatter: function() {
                 var s;
                 if (this.point.name) { // the pie chart
                     s = ''+
                         this.point.name +': '+ timeFormat(this.y) + pieToolTip;
                 } else {
                     s = ''+
                         this.x  +': '+ $.formatNumber( this.y, {format:"#,###"} );
                 }
                 return s;
             }
         },
         labels: {
             items: [{
                 html: pieTitle,
                 style: {
                     left: '689px',
                     top: '8px',
                     color: 'black'
                 }
             }]
         },
         series: serise
    });

	return chart;
}

function makeDefaultLineChartToday(chart, renderTo, title, subtitle, seriesname, cate, xstep, data) {

	chart = new Highcharts.Chart({
		chart: {
            renderTo: renderTo,
            zoomType: 'x',
            type: 'line'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate,
            labels: {
                step: xstep
            }
        },
        yAxis: {
        	min:0,
            title: {
                text: seriesname
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
            	return '<b>'+ this.series.name +'</b><br/>'+
				this.x +': '+ $.formatNumber(this.y, {format:"#,##0", locale:"us"});
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: false
                },
                marker: {
                    enabled: true,
                    states: {
                        hover: {
                            enabled: true,
                            radius: 5
                        }
                    }
                },
                enableMouseTracking: true
            }
        },
        series: [{
            name: seriesname,
            data: data
        }]
    });

	return chart;
}

function makeDefaultLineChart(chart, renderTo, title, subtitle, seriesname, cate, xstep, data, avg) {

	chart = new Highcharts.Chart({
		chart: {
            renderTo: renderTo,
            zoomType: 'x',
            type: 'line'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate,
            labels: {
                step: xstep
            }
        },
        yAxis: {
        	min:0,
            title: {
                text: seriesname
            },
            plotLines:  [{
            	value: avg,
            	color: 'orange',
                label: {
                	align: 'right',
                    text : 'Avg',
                    style: {
                    	color: 'orange',
                    	fontWeight: 'bold'
                    }
                },
                width: 2,
                id: 'avg-plot-line'
            }]
        },
        tooltip: {
            enabled: true,
            formatter: function() {
            	return '<b>'+ this.series.name +'</b><br/>'+
				this.x +': '+ $.formatNumber(this.y, {format:"#,##0.##", locale:"us"});
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: false
                },
                marker: {
                    enabled: true,
                    states: {
                        hover: {
                            enabled: true,
                            radius: 5
                        }
                    }
                },
                enableMouseTracking: true
            }
        },
        series: [{
            name: seriesname,
            data: data
        }]
    });

	return chart;
}

function makeMultiLineChart(chart, renderTo, title, subtitle, seriesname, cate, xstep, data_array) {

	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            zoomType: 'x',
            type: 'line'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate,
            labels: {
                step: xstep
            }
        },
        yAxis: {
            title: {
                text: seriesname
            },
            min:0
        },
        tooltip: {
        	shared: true,
        	crosshairs: true,
            pointFormat: '<span style="color:{series.color}">{series.name}</span>:{point.y}<br/>'
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: false
                },
                marker: {
                    enabled: true,
                    states: {
                        hover: {
                            enabled: true,
                            radius: 5
                        }
                    }
                },
                enableMouseTracking: true
            }
        },
        series: data_array
    });

	return chart;
}

function makeDefaultPieChart(chart, renderTo, title, subtitle, data) {

	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title
        },
        subtitle: {
        	text: subtitle
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.point.name +'</b>: '+ $.formatNumber(this.y, {format:"#,##0", locale:"us"}) + '('+ $.formatNumber( this.percentage, {format:"#0.0", locale:"us"} ) +' %)';
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    formatter: function() {
                    	return $.formatNumber( this.percentage, {format:"#0.0", locale:"us"} ) +' %';
                    }
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            data: data
        }]
    });

	return chart;
}

function makeOverlabelPieChart(chart, renderTo, title, seriesname, data) {

	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title
        },
        subtitle: {
        	text: "Source:Fingra.ph Opensource"
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.point.name +'</b>: '+ this.y + '('+ $.formatNumber( this.percentage, {format:"#0.0", locale:"us"} ) +' %)';
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    distance: 20,
                    padding: 3,
                    connectorPadding: 4
                },
                showInLegend: false
            }
        },
        series: [{
            type: 'pie',
            name: seriesname,
            data: data
        }]
    });

	return chart;
}

function makeDefaultColumnChart(chart, renderTo, title, subtitle, seriesname, cate, data, total) {

	var xLabelsRotation = 0;
	var xLabelsalign =	'center';
	if(cate.length>10){
		xLabelsRotation = -45;
		xLabelsalign =	'right';
	}

	chart = new Highcharts.Chart({
		chart: {
            renderTo: renderTo,
            type: 'column'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate,
            labels: {
            	rotation: xLabelsRotation,
                align: xLabelsalign
            }
        },
        yAxis: {
            title: {
                text: seriesname
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y;
                    }
                }
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
            	var text = '<b>'+ this.x +'</b>: '+ $.formatNumber(this.y, {format:"#,##0", locale:"us"});
            	if(total>0){
            		text = text + ' ('+ $.formatNumber(this.y/total*100, {format:"0.#", locale:"us"}) +'%)';
            	}
                return text;
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: seriesname,
            data: data
        }]
    });

	return chart;
}

function makeDefaultColumnChartToday(chart, renderTo, title, subtitle, seriesname, cate, data, total) {

	chart = new Highcharts.Chart({
		chart: {
            renderTo: renderTo,
            type: 'column'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate,
        },
        yAxis: {
            title: {
                text: seriesname
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y;
                    }
                }
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
            	var text = '<b>'+ this.x +'</b>: '+ $.formatNumber(this.y, {format:"#,##0", locale:"us"});
            	if(total>0){
            		text = text + ' ('+ $.formatNumber(this.y/total*100, {format:"0.#", locale:"us"}) +'%)';
            	}
                return text;
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: seriesname,
            data: data
        }]
    });

	return chart;
}

function getPercentage(value,total){
	return $.formatNumber(value/total*100, {format:"0.#", locale:"us"});
}

function makeDefaultColumnPercentChart(chart, renderTo, title, subtitle, seriesname, cate, data) {

	chart = new Highcharts.Chart({
		chart: {
            renderTo: renderTo,
            type: 'column'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate
        },
        yAxis: {
        	max:100,
        	min:0,
            title: {
                text: seriesname
            },
            labels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                },
                formatter:function() {
                	return this.value +'%';
                }
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter:function() {
                    	return $.formatNumber( this.y, {format:"#,###.00"})+'%';
                    }
                }
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
                return '<b>'+ this.x +'</b>: '+ $.formatNumber( this.y, {format:"#,###.00"}) +'%';
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: seriesname,
            data: data
        }]
    });

	return chart;
}

function makeMultiColumnChart(chart, renderTo, title, subtitle, seriesname, cate, data_array) {

	chart = new Highcharts.Chart({
		chart: {
            renderTo: renderTo,
            type: 'column'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate
        },
        yAxis: {
            title: {
                text: seriesname
            }
        },
        plotOptions: {
            column: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +'%';
                    }
                }
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
                return '<b>'+ this.x +'</b>: '+ this.y;
            }
        },
        series: data_array
    });

	return chart;
}

//countryDetail activeUser
function makeStackedColumnChart(chart, renderTo, title, subtitle, seriesname, cate,xstep, data_array) {

	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'column'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
            categories: cate,
            labels: {
                step: xstep==''?'':xstep
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: seriesname
            },
            stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                },
                formatter:function() {
                	return $.formatNumber( this.total, {format:"#,###"} );
                }
            }
        },
        tooltip: {
        	shared: true,
            pointFormat: '<span style="color:{series.color}">{series.name}</span>:{point.y} <br/>'
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: false,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                    formatter:function() {
                    	return $.formatNumber( this.y, {format:"#,###"} );
                    }
                }
            }
        },
        series: data_array
    });

	return chart;
}

function makeScatterChart(chart, renderTo, title, subtitle, xtitle, ytitle, cate, xstep,
		s1title, s1data, s2title, s2data, s3title, s3data) {

	var new_s1data = new Array(s1data.length);
    var new_s2data = new Array(s2data.length);
    var new_s3data = new Array(s3data.length);

    var i=0;
    for (i=0; i<cate.length; i++) {
    	new_s1data[i] = [i, parseInt(s1data[i])];
    }
    for (i=0; i<cate.length; i++) {
    	new_s2data[i] = [cate[i], parseInt(s2data[i])];
    }
    for (i=0; i<cate.length; i++) {
    	new_s3data[i] = [cate[i], parseInt(s3data[i])];
    }

	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'scatter',
            zoomType: 'x'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: {
        	categories: cate,
            labels: {
                step: xstep
            },
            title: {
                text: xtitle
            }
        },
        yAxis: {
            title: {
                text: ytitle
            }
        },
        tooltip: {
        	enabled: true,
            formatter: function() {
            	return '<b>'+ this.series.name +'</b><br/>'+
				this.x +' : '+ this.y;
            }
        },
        plotOptions: {
            scatter: {
                marker: {
                    radius: 5,
                    states: {
                        hover: {
                            enabled: true,
                            lineColor: 'rgb(100,100,100)'
                        }
                    }
                },
                states: {
                    hover: {
                        marker: {
                            enabled: false
                        }
                    }
                }
            }
        },
        series: [{
            name: s1title,
            color: 'rgba(223, 83, 83, .6)',
            data: new_s1data
        }, {
            name: s2title,
            color: 'rgba(119, 152, 191, .6)',
            data: new_s2data
        }, {
            name: s3title,
            color: 'rgba(169, 112, 131, .6)',
            data: new_s3data
        }]
    });

	return chart;
}

function makeDefaultAreaChart(chart, renderTo, title, subtitle, seriesname, cate, xstep, data_array) {

	chart = new Highcharts.Chart({
         chart: {
        	 renderTo: renderTo,
             type: 'area'
         },
         title: {
             text: title
         },
         subtitle: {
             text: subtitle
         },
         xAxis: {
        	 categories: cate,
             title: {
                 enabled: false
             },
             labels: {
            	 step: xstep,
                 formatter: function() {
                     return this.value; // clean, unformatted number for year
                 }
             }
         },
         yAxis: {
        	 min:0,
             title: {
                 text: seriesname
             },
             labels: {
                 formatter: function() {
                     return this.value;
                 }
             }
         },
         tooltip: {
        	 shared: true,
        	 crosshairs: true,
             pointFormat: '<span style="color:{series.color}">{series.name}</span>:{point.y}<br/>'
         },
         plotOptions: {
             area: {
            	 stacking: 'normal',
                 lineColor: '#ffffff',
                 lineWidth: 2,
                 marker: {
                     enabled: false,
                     radius: 3
                 }
              }
         },
         series: data_array
	});

	return chart;
}

function makeLineAndColumnChart(chart, renderTo, title, subtitle, primarySeriesName,secondarySeriesName, cate, columnData_array,lineData_array) {

	chart = new Highcharts.Chart({
		chart: {
			renderTo: renderTo,
            zoomType: 'xy'
        },
        title: {
            text: title
        },
        subtitle: {
            text: subtitle
        },
        xAxis: [{
            categories: cate
        }],
        yAxis: [{ // Primary yAxis
        	min: 0,
        	labels: {
	            format: '{value}',
	            style: {
	                color: '#89A54E'
	            }
	        },
	        title: {
	            text: primarySeriesName,
	            style: {
	                color: '#89A54E'
	            }
	        }
        }, {// Secondary yAxis
        	min: 0,
        	labels: {
                format: '{value}',
                style: {
                    color: '#4572A7'
                }
            },
        	title: {
                text: secondarySeriesName,
                style: {
                    color: '#4572A7'
                }
	        },
            opposite: true
        }],
        tooltip: {
            shared: true
        },
        series: [{
            name: secondarySeriesName,
            color: '#4572A7',
            type: 'column',
            yAxis: 1,
            data: columnData_array
        }, {
            name: primarySeriesName,
            color: '#89A54E',
            type: 'spline',
            data: lineData_array
        }]
	});
}

// ie7's array indexOf problem
if (!Array.prototype.indexOf)
{
  Array.prototype.indexOf = function(elt /*, from*/)
  {
    var len = this.length;

    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;

    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}

function makeLostStepColumnChart(chart, renderTo, title, subtitle, seriesname, cate, data){

	chart = new Highcharts.Chart({
			chart: {type: 'column', renderTo: renderTo},
			title: {text: title},
			subtitle: {
	            text: subtitle
	        },
		    xAxis: {
		        categories: cate
		    },
			yAxis: {
	       	    title: {
	                text: seriesname
	            },
	            labels: {
	                formatter: function() {
	                    return this.value;
	                }
	            }
	        },
		    tooltip: {
	            enabled: false
	        },
		    plotOptions: {
		    	series: {
	                dataLabels: {
	                    enabled: true,
	                    useHTML:true,
	                    borderRadius: 5,
	                    backgroundColor: 'rgba(252, 255, 197, 0.7)',
	                    borderWidth: 2,
	                    borderColor: '#0079c1',
	                    y: -6,
	                    shadow:true,
	                    padding:5
	                    ,
	                    formatter: function() {
	                        return this.point.name+'<br/>' + '<font color="#0079c1">'+this.series.name +' : '+ $.formatNumber(this.y, {format:"#,##0", locale:"us"})+'</font>';
	                    }
	                }
	            }
	        },
		    series: [{
		    	name: seriesname,
		        data: data
		    }]
	});

}

function makeCompareColumnChart(chart,renderTo,title,subtitle,seriesname,cate,data,type){            

	var ch = {
			renderTo:renderTo,
			type: typeof(type) === 'undefined' ? 'column' : 'bar'
		};
	if (chart !== null){ ch.height = chart.height; }

	chart = new Highcharts.Chart({
		chart: ch,
		title: {
			text:title
		},
		subtitle:{
			text:subtitle
		},
		xAxis:{
			categories:cate,
			labels: {
				formatter:function() {
					var text = this.value;
					var formatted = text.length > 30 ? text.substring(0,30)+'...' : text;
					return formatted;
				}
			}
		},
		yAxis:{
			title:{
				text:seriesname
			}
		},
		plotOptions:{
			column:{
				cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function(){
                    	return this.y;
                    }
                }
			},
			bar :{
				cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function(){
                    	return this.y;
                    }
                }
			}
		},
		tooltip:{
			 formatter:function(){
				 var s = [];
				 var red='#e4582b';
				 var blue='#0079c1';
				 $.each(this.points, function(i, point) {
					 var color = '#ffffff';
					 if (i==0){
						 color = red;
					 } else if (i==1) {
						 color = blue;
					 }
					 s.unshift('<span style="color:'+color+';font-weight:bold;">'+ point.series.name +' : '+ point.y +'<span>');
				 });
             
				 return this.x+'<br/><br/>'+s.join('<br />');
			 },
			 shared:true
		},
		series:data
	});
}

function makeMultiStackedBarChart(chart,renderTo,title,subtitle,seriesname,cate,data){

	chart = new Highcharts.Chart({
        chart: {
            type: 'bar',
            renderTo:renderTo,
            height: 60*cate.length+150
        },
        title: {text:title},
        subtitle: {
            text: subtitle
        },
        xAxis:{
            categories:cate
        },
        yAxis:{
            min:0,
            title:{
                text: seriesname
            }
        },
        plotOptions: {
            series:{
                stacking: 'normal'
            },
            bar:{
                shadow:false
            }
        },
        tooltip: {
            enabled: true,
            shared:true,
            formatter: function() {
                var tooltip = this.x+'<br />';
                for (var i=0;i<this.points.length;i++){
                    tooltip=tooltip+'<span style="color:'+Highcharts.theme.colors[i]+';font-weight:bold;">'+ this.points[i].series.name +'</span>: '+ this.points[i].y + '<br />';
                }
                return tooltip;
            }
        },
        series:data
    });

    return chart;
}

function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}

//max,min icon(known)
function makeKnowMaxMinDataSymbol(data,max,min){
	var rootPath = window.location.protocol + "//" + window.location.host + getContextPath();
	var resourcePath = rootPath + "/resources/img";
	
	data[data.indexOf(max)]={y:max, marker: {symbol: 'url('+resourcePath+'/dot_max.png)'}};
	data[data.indexOf(min)]={y:min, marker: {symbol: 'url('+resourcePath+'/dot_min.png)'}};
	return data;
}

//max,min icon(unknown)
function makeMaxMinDataSymbol(data){
	var rootPath = window.location.protocol + "//" + window.location.host + getContextPath();
	var resourcePath = rootPath + "/resources/img";
	
	var max=0;
	var min=99999999;

	for(var i=0;i<data.length;i++){
		if(max<data[i]) max=data[i];
		if(min>data[i]) min=data[i];
	}
	data[data.indexOf(max)]={y:max, marker: {symbol: 'url('+resourcePath+'/dot_max.png)'}};
	data[data.indexOf(min)]={y:min, marker: {symbol: 'url('+resourcePath+'/dot_min.png)'}};
	return data;
}

function timeFormat(sec) {
	var min = 0;
	var hour = 0;
	var day = 0;

	var format_string;

	if(sec >= 60) {
		min = (sec - (sec % 60)) / 60;
		sec = sec % 60;

		if(min >= 60) {
			hour = (min - (min % 60)) / 60;
			min = min % 60;

			if(hour >= 24) {
				day = (hour - (hour % 24)) / 24;
				hour = hour % 24;
			}
		}
	}

	format_string = sec + 's';

	if(min || hour || day ) {
		format_string = min + 'm ' + format_string;

		if(hour || day) {
			format_string = hour + 'h ' + format_string;
		}

		if(day) {
			format_string = day + 'd ' + format_string;
		}
	}

	return format_string;
}

var isoLangs = {
		"aa": "Afar",
		"ab": "Abkhazian",
		"ae": "Avestan",
		"af": "Afrikaans",
		"ak": "Akan",
		"am": "Amharic",
		"an": "Aragonese",
		"ar": "Arabic",
		"as": "Assamese",
		"ay": "Aymara",
		"az": "Azerbaijani",
		"ba": "Bashkir",
		"be": "Byelorussian",
		"bg": "Bulgarian",
		"bh": "Bihari",
		"bi": "Bislama",
		"bm": "Bambara",
		"bn": "Bengali, Bangla",
		"bo": "Tibetan",
		"br": "Breton",
		"bs": "Bosnian",
		"ca": "Catalan",
		"ce": "Chechen",
		"ch": "Chamorro",
		"co": "Corsican",
		"cr": "Cree",
		"cs": "Czech",
		"cu": "Old Church Slavonic, Church Slavic, Church Slavonic, Old Bulgarian, Old Slavonic",
		"cv": "Chuvash",
		"cy": "Welsh",
		"da": "Danish",
		"de": "German",
		"dv": "Divehi; Dhivehi; Maldivian;",
		"dz": "Bhutani",
		"ee": "Ewe",
		"el": "Greek",
		"en": "English, American",
		"eo": "Esperanto",
		"es": "Spanish",
		"et": "Estonian",
		"eu": "Basque",
		"fa": "Persian",
		"ff": "Fula; Fulah; Pulaar; Pular",
		"fi": "Finnish",
		"fj": "Fiji",
		"fo": "Faeroese",
		"fr": "French",
		"fy": "Frisian",
		"ga": "Irish",
		"gd": "Gaelic (Scots Gaelic)",
		"gl": "Galician",
		"gn": "Guarani",
		"gu": "Gujarati",
		"gv": "Manx",
		"ha": "Hausa",
		"he": "Hebrew (modern)",
		"hi": "Hindi",
		"ho": "Hiri Motu",
		"hr": "Croatian",
		"ht": "Haitian; Haitian Creole",
		"hu": "Hungarian",
		"hy": "Armenian",
		"hz": "Herero",
		"ia": "Interlingua",
		"id": "Indonesian",
		"ie": "Interlingue",
		"ig": "Igbo",
		"ik": "Inupiak",
		"in": "Indonesian",
		"io": "Ido",
		"is": "Icelandic",
		"it": "Italian",
		"iu": "Inuktitut",
		"iw": "Hebrew",
		"ja": "Japanese",
		"ji": "Yiddish",
		"jv": "Javanese",
		"jw": "Javanese",
		"ka": "Georgian",
		"kg": "Kongo",
		"ki": "Kikuyu, Gikuyu",
		"kj": "Kwanyama, Kuanyama",
		"kk": "Kazakh",
		"kl": "Greenlandic",
		"km": "Cambodian",
		"kn": "Kannada",
		"ko": "Korean",
		"kr": "Kanuri",
		"ks": "Kashmiri",
		"ku": "Kurdish",
		"kv": "Komi",
		"kw": "Cornish",
		"ky": "Kirghiz",
		"la": "Latin",
		"lb": "Luxembourgish, Letzeburgesch",
		"lg": "Luganda",
		"li": "Limburgish, Limburgan, Limburger",
		"ln": "Lingala",
		"lo": "Laothian",
		"lt": "Lithuanian",
		"lu": "Luba-Katanga",
		"lv": "Latvian, Lettish",
		"mg": "Malagasy",
		"mh": "Marshallese",
		"mi": "Maori",
		"mk": "Macedonian",
		"ml": "Malayalam",
		"mn": "Mongolian",
		"mo": "Moldavian",
		"mr": "Marathi",
		"ms": "Malay",
		"mt": "Maltese",
		"my": "Burmese",
		"na": "Nauru",
		"nb": "Norwegian Bokmål",
		"nd": "North Ndebele",
		"ne": "Nepali",
		"ng": "Ndonga",
		"nl": "Dutch",
		"nn": "Norwegian Nynorsk",
		"no": "Norwegian",
		"nr": "South Ndebele",
		"nv": "Navajo, Navaho",
		"ny": "Chichewa; Chewa; Nyanja",
		"oc": "Occitan",
		"oj": "Ojibwe, Ojibwa",
		"om": "Oromo, Afan",
		"or": "Oriya",
		"os": "Ossetian, Ossetic",
		"pa": "Punjabi",
		"pi": "Pāli",
		"pl": "Polish",
		"ps": "Pashto, Pushto",
		"pt": "Portuguese",
		"qu": "Quechua",
		"rm": "Rhaeto-Romance",
		"rn": "Kirundi",
		"ro": "Romanian",
		"ru": "Russian",
		"rw": "Kinyarwanda",
		"sa": "Sanskrit",
		"sc": "Sardinian",
		"sd": "Sindhi",
		"se": "Northern Sami",
		"sg": "Sangro",
		"sh": "Serbo-Croatian",
		"si": "Singhalese",
		"sk": "Slovak",
		"sl": "Slovenian",
		"sm": "Samoan",
		"sn": "Shona",
		"so": "Somali",
		"sq": "Albanian",
		"sr": "Serbian",
		"ss": "Siswati",
		"st": "Sesotho",
		"su": "Sudanese",
		"sv": "Swedish",
		"sw": "Swahili",
		"ta": "Tamil",
		"te": "Tegulu",
		"tg": "Tajik",
		"th": "Thai",
		"ti": "Tigrinya",
		"tk": "Turkmen",
		"tl": "Tagalog",
		"tn": "Setswana",
		"to": "Tonga",
		"tr": "Turkish",
		"ts": "Tsonga",
		"tt": "Tatar",
		"tw": "Twi",
		"ty": "Tahitian",
		"ug": "Uighur, Uyghur",
		"uk": "Ukrainian",
		"ur": "Urdu",
		"uz": "Uzbek",
		"ve": "Venda",
		"vi": "Vietnamese",
		"vo": "Volapuk",
		"wa": "Walloon",
		"wo": "Wolof",
		"xh": "Xhosa",
		"yi": "Yiddish",
		"yo": "Yoruba",
		"za": "Zhuang, Chuang",
		"zh": "Chinese",
		"zu": "Zulu"
};

var iosCountries = {
		"A1": "Anonymous Proxy",
		"A2": "Satellite Provider",
		"O1": "Other Country",
		"AC": "Ascension Island",
		"AD": "Andorra",
		"AE": "United Arab Emirates",
		"AF": "Afghanistan",
		"AG": "Antigua and Barbuda",
		"AI": "Anguilla",
		"AL": "Albania",
		"AM": "Armenia",
		"AN": "Netherlands Antilles",
		"AO": "Angola",
		"AP": "Asia/Pacific Region",
		"AQ": "Antarctica",
		"AR": "Argentina",
		"AS": "American Samoa",
		"AT": "Austria",
		"AU": "Australia",
		"AW": "Aruba",
		"AX": "Aland Islands",
		"AZ": "Azerbaijan",
		"BA": "Bosnia and Herzegovina",
		"BB": "Barbados",
		"BD": "Bangladesh",
		"BE": "Belgium",
		"BF": "Burkina Faso",
		"BG": "Bulgaria",
		"BH": "Bahrain",
		"BI": "Burundi",
		"BJ": "Benin",
		"BM": "Bermuda",
		"BN": "Brunei Darussalam",
		"BO": "Bolivia",
		"BR": "Brazil",
		"BS": "Bahamas",
		"BT": "Bhutan",
		"BV": "Bouvet Island",
		"BW": "Botswana",
		"BY": "Belarus",
		"BZ": "Belize",
		"CA": "Canada",
		"CC": "Cocos (Keeling) Islands",
		"CD": "Congo, The Democratic Republic of the",
		"CF": "Central African Republic",
		"CG": "Congo",
		"CH": "Switzerland",
		"CI": "Cote d'Ivoire",
		"CK": "Cook Islands",
		"CL": "Chile",
		"CM": "Cameroon",
		"CN": "China",
		"CO": "Colombia",
		"CR": "Costa Rica",
		"CS": "Serbia and Montenegro",
		"CU": "Cuba",
		"CV": "Cape Verde",
		"CX": "Christmas Island",
		"CY": "Cyprus",
		"CZ": "Czech Republic",
		"DE": "Germany",
		"DJ": "Djibouti",
		"DK": "Denmark",
		"DM": "Dominica",
		"DO": "Dominican Republic",
		"DZ": "Algeria",
		"EC": "Ecuador",
		"EE": "Estonia",
		"EG": "Egypt",
		"EH": "Western Sahara",
		"ER": "Eritrea",
		"ES": "Spain",
		"ET": "Ethiopia",
		"EU": "Europe",
		"FI": "Finland",
		"FJ": "Fiji",
		"FK": "Falkland Islands (Malvinas)",
		"FM": "Micronesia, Federated States of",
		"FO": "Faroe Islands",
		"FR": "France",
		"GA": "Gabon",
		"GB": "United Kingdom",
		"GD": "Grenada",
		"GE": "Georgia",
		"GF": "French Guiana",
		"GG": "Guernsey",
		"GH": "Ghana",
		"GI": "Gibraltar",
		"GL": "Greenland",
		"GM": "Gambia",
		"GN": "Guinea",
		"GP": "Guadeloupe",
		"GQ": "Equatorial Guinea",
		"GR": "Greece",
		"GS": "South Georgia and the South Sandwich Islands",
		"GT": "Guatemala",
		"GU": "Guam",
		"GW": "Guinea-Bissau",
		"GY": "Guyana",
		"HK": "Hong Kong",
		"HM": "Heard Island and McDonald Islands",
		"HN": "Honduras",
		"HR": "Croatia",
		"HT": "Haiti",
		"HU": "Hungary",
		"ID": "Indonesia",
		"IE": "Ireland",
		"IL": "Israel",
		"IM": "Isle of Man",
		"IN": "India",
		"IO": "British Indian Ocean Territory",
		"IQ": "Iraq",
		"IR": "Iran, Islamic Republic of",
		"IS": "Iceland",
		"IT": "Italy",
		"JE": "Jersey",
		"JM": "Jamaica",
		"JO": "Jordan",
		"JP": "Japan",
		"KE": "Kenya",
		"KG": "Kyrgyzstan",
		"KH": "Cambodia",
		"KI": "Kiribati",
		"KM": "Comoros",
		"KN": "Saint Kitts and Nevis",
		"KP": "Korea, Democratic People's Republic of",
		"KR": "Korea, Republic of",
		"KW": "Kuwait",
		"KY": "Cayman Islands",
		"KZ": "Kazakhstan",
		"LA": "Lao People's Democratic Republic",
		"LB": "Lebanon",
		"LC": "Saint Lucia",
		"LI": "Liechtenstein",
		"LK": "Sri Lanka",
		"LR": "Liberia",
		"LS": "Lesotho",
		"LT": "Lithuania",
		"LU": "Luxembourg",
		"LV": "Latvia",
		"LY": "Libyan Arab Jamahiriya",
		"MA": "Morocco",
		"MC": "Monaco",
		"MD": "Moldova, Republic of",
		"ME": "Montenegro",
		"MG": "Madagascar",
		"MH": "Marshall Islands",
		"MK": "Macedonia",
		"ML": "Mali",
		"MM": "Myanmar",
		"MN": "Mongolia",
		"MO": "Macao",
		"MP": "Northern Mariana Islands",
		"MQ": "Martinique",
		"MR": "Mauritania",
		"MS": "Montserrat",
		"MT": "Malta",
		"MU": "Mauritius",
		"MV": "Maldives",
		"MW": "Malawi",
		"MX": "Mexico",
		"MY": "Malaysia",
		"MZ": "Mozambique",
		"NA": "Namibia",
		"NC": "New Caledonia",
		"NE": "Niger",
		"NF": "Norfolk Island",
		"NG": "Nigeria",
		"NI": "Nicaragua",
		"NL": "Netherlands",
		"NO": "Norway",
		"NP": "Nepal",
		"NR": "Nauru",
		"NU": "Niue",
		"NZ": "New Zealand",
		"OM": "Oman",
		"PA": "Panama",
		"PE": "Peru",
		"PF": "French Polynesia",
		"PG": "Papua New Guinea",
		"PH": "Philippines",
		"PK": "Pakistan",
		"PL": "Poland",
		"PM": "Saint Pierre and Miquelon",
		"PN": "Pitcairn",
		"PR": "Puerto Rico",
		"PS": "Palestinian Territory",
		"PT": "Portugal",
		"PW": "Palau",
		"PY": "Paraguay",
		"QA": "Qatar",
		"RE": "Reunion",
		"RO": "Romania",
		"RS": "Serbia",
		"RU": "Russian Federation",
		"RW": "Rwanda",
		"SA": "Saudi Arabia",
		"SB": "Solomon Islands",
		"SC": "Seychelles",
		"SD": "Sudan",
		"SE": "Sweden",
		"SG": "Singapore",
		"SH": "Saint Helena",
		"SI": "Slovenia",
		"SJ": "Svalbard and Jan Mayen",
		"SK": "Slovakia",
		"SL": "Sierra Leone",
		"SM": "San Marino",
		"SN": "Senegal",
		"SO": "Somalia",
		"SR": "Suriname",
		"ST": "Sao Tome and Principe",
		"SV": "El Salvador",
		"SY": "Syrian Arab Republic",
		"SZ": "Swaziland",
		"TC": "Turks and Caicos Islands",
		"TD": "Chad",
		"TF": "French Southern Territories",
		"TG": "Togo",
		"TH": "Thailand",
		"TJ": "Tajikistan",
		"TK": "Tokelau",
		"TL": "Timor-Leste",
		"TM": "Turkmenistan",
		"TN": "Tunisia",
		"TO": "Tonga",
		"TP": "East Timor",
		"TR": "Turkey",
		"TT": "Trinidad and Tobago",
		"TV": "Tuvalu",
		"TW": "Taiwan",
		"TZ": "Tanzania, United Republic of",
		"UA": "Ukraine",
		"UG": "Uganda",
		"UK": "United Kingdom",
		"UM": "United States Minor Outlying Islands",
		"US": "United States",
		"UY": "Uruguay",
		"UZ": "Uzbekistan",
		"VA": "Holy See (Vatican City State)",
		"VC": "Saint Vincent and the Grenadines",
		"VE": "Venezuela",
		"VG": "Virgin Islands, British",
		"VI": "Virgin Islands, U.S.",
		"VN": "Vietnam",
		"VU": "Vanuatu",
		"WF": "Wallis and Futuna",
		"WS": "Samoa",
		"YE": "Yemen",
		"YT": "Mayotte",
		"YU": "Yugoslavia",
		"ZA": "South Africa",
		"ZM": "Zambia",
		"ZW": "Zimbabwe"
};
