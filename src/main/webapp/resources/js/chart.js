var period;
var term;
var segment;
$(function () {

	// chart theme
	Highcharts.theme = {
			colors: ['#0079c1', '#a9cc5b', '#e4582b', '#f8c624', '#49bb93', '#b457c2', '#ef4c82', '#a6b9bc', '#a9cc5b', '#3ac6e2', '#6060e8', '#ef6f6f' ]
		//	colors: ['#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92']
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

	// 차트를 만들어준다.
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
        // plotOptions: {column: {stacking: 'normal',dataLabels: {enabled: false,color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'}}},
         credits: { enabled: false },
         series: serise    });

	return chart;
}


function makeDefaultLineChartToday(chart, renderTo, title, subtitle, seriesname, cate, xstep, data) {

	// 차트를 만들어준다.
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

        }],
        credits: { enabled: false }

    });

	return chart;
}


function makeDefaultLineChart(chart, renderTo, title, subtitle, seriesname, cate, xstep, data, avg) {
	// 차트를 만들어준다.
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

        }],
        credits: { enabled: false }

    });

	return chart;
}

function makeMultiLineChart(chart, renderTo, title, subtitle, seriesname, cate, xstep, data_array) {
	// 차트를 만들어준다.
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
        	/*
            enabled: true,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
				this.x +': '+ this.y;
            }
            */
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
        series: data_array,
        /*
        series: [{
            name: seriesname,
            data: data_array
        }],
        */
        credits: { enabled: false }

    });

	return chart;
}

function makeDefaultPieChart(chart, renderTo, title, subtitle, data) {
	// 차트를 만들어준다.
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
                        //return '<b>'+ this.point.name +'</b>: '+ this.y + '('+ $.formatNumber( this.percentage, {format:"#0.0", locale:"us"} ) +' %)';
                    	return $.formatNumber( this.percentage, {format:"#0.0", locale:"us"} ) +' %';
                    }
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
        //  name: seriesname,
            data: data
        }],
        credits: { enabled: false }

    });

	return chart;
}

function makeOverlabelPieChart(chart, renderTo, title, seriesname, data) {
	// 차트를 만들어준다.
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
        	text: "Source:<a href='http://fingra.ph'>Fingra.ph</a>"
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
        }],
        credits: { enabled: false }

    });

	return chart;
}



function makeDefaultColumnChart(chart, renderTo, title, subtitle, seriesname, cate, data, total) {
	// x축 cate가 많아지면 겹쳐서표시되는걸 방지
	var xLabelsRotation = 0;
	var xLabelsalign =	'center';
	if(cate.length>10){
		xLabelsRotation = -45;
		xLabelsalign =	'right';
	}

	// 차트를 만들어준다.
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
            	if(total>0){ //total값이 주어지면 백분율계산 추가
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
        }],
        credits: { enabled: false }

    });

	return chart;
}

function makeDefaultColumnChartToday(chart, renderTo, title, subtitle, seriesname, cate, data, total) {

	// 차트를 만들어준다.
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
            	if(total>0){ //total값이 주어지면 백분율계산 추가
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
        }],
        credits: { enabled: false }

    });

	return chart;
}
function getPercentage(value,total){
	return $.formatNumber(value/total*100, {format:"0.#", locale:"us"});
}

function makeDefaultColumnPercentChart(chart, renderTo, title, subtitle, seriesname, cate, data) {
	// 차트를 만들어준다.
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
        }],
        credits: { enabled: false }

    });

	return chart;
}

function makeMultiColumnChart(chart, renderTo, title, subtitle, seriesname, cate, data_array) {
	// 차트를 만들어준다.
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
                //,pointPadding: -0.4,
                //borderWidth: 0
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
                return '<b>'+ this.x +'</b>: '+ this.y;
            }

        },
        series: data_array,
        credits: { enabled: false }

    });

	return chart;
}

//countryDetail activeUser
function makeStackedColumnChart(chart, renderTo, title, subtitle, seriesname, cate,xstep, data_array) {
	// 차트를 만들어준다.
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
            /*
            formatter: function() {
                return '<b>'+ this.series.name + '</b>' + ': '+ $.formatNumber( this.y, {format:"#,###"} ) +
                	'('+ $.formatNumber( ((this.y * 100) /this.point.stackTotal), {format:"#0.0", locale:"us"} ) +' %)';
            }
            */
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
        series: data_array,
        credits: { enabled: false }
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

	// 차트를 만들어준다.
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
                //rotation: -25,
                //align: 'right'
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
        }],
        credits: { enabled: false }
    });

	return chart;
}


function makeDefaultAreaChart(chart, renderTo, title, subtitle, seriesname, cate, xstep, data_array) {

	// 차트를 만들어준다.
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
        	 //tickmarkPlacement: 'on',
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
                 //pointStart: 1940,
            	 stacking: 'normal',
                 lineColor: '#ffffff',
                 lineWidth: 2,
                 marker: {
                     enabled: false,
                     radius: 3
                 }
              }
         },
         series: data_array,
         credits: { enabled: false }

	});

	return chart;
}

function makeLineAndColumnChart(chart, renderTo, title, subtitle, primarySeriesName,secondarySeriesName, cate, columnData_array,lineData_array) {
	// 차트를 만들어준다.
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
        }],
        credits: { enabled: false }

	});
}

// ie7에서 array 의 indexOf 문제
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
		    }],
			credits: { enabled: false }
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
		series:data,
		credits:{enabled:false}
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
        series:data,
        credits:{enabled:false}
    });

    return chart;
}


//최대,최소 (알때)아이콘 표시
function makeKnowMaxMinDataSymbol(data,max,min){

	 data[data.indexOf(max)]={y:max, marker: {symbol: 'url(http://www.fingra.ph/resources/img/dot_max.png)'}};
	 data[data.indexOf(min)]={y:min, marker: {symbol: 'url(http://www.fingra.ph/resources/img/dot_min.png)'}};
	return data;
}
//최대,최소 (모를때)아이콘 표시
function makeMaxMinDataSymbol(data){
	var max=0;
	var min=99999999;

	 for(var i=0;i<data.length;i++){
		 if(max<data[i]) max=data[i];
		 if(min>data[i]) min=data[i];
	 }
	 data[data.indexOf(max)]={y:max, marker: {symbol: 'url(http://www.fingra.ph/resources/img/dot_max.png)'}};
	 data[data.indexOf(min)]={y:min, marker: {symbol: 'url(http://www.fingra.ph/resources/img/dot_min.png)'}};
	return data;
}

/*
 fingraph-statConfig.js로 이동

function addPeriods(periodVal){
	var type = periodVal.split('-');
	var to = moment().subtract('d', 1);
	var from = "";
	if(type[1]=='d'){
		from = moment().subtract('d', eval(type[0])).format('YYYY-MM-DD');
	}else if(type[1]=='m'){
		from = moment().subtract('d', 1).subtract('M',eval(type[0])).format('YYYY-MM-DD');
	}else if(type[1]=='y'){
		from = moment().subtract('d', 1).year() + '-01-01';
	}else{
		$('.calImg').css('display','');
		return;
	}

	$('#fromTo').val(from +' ~ '+to.format('YYYY-MM-DD'));
	$('#from').val(from);
	$('#to').val(to.format('YYYY-MM-DD'));
	setPeriodCookie($('#fromTo').val(),$('#period').val());


}
*/


/*
function trimCategoryList(cate_list, max_length) {
	var total_length = 0;
	trim_list = new Array(cate_list.length);

	for(var j=1;j<cate_list.length; j++) {
		total_length = 0;

		for(var i=0; i<cate_list.length; i++) {
			if(i%j==0) {
				var cate = cate_list[i];
				total_length = total_length + cate.length;
				trim_list[i] = cate;
			} else {
				trim_list[i] = "";
			}

		}

		if(total_length<max_length) {
			break;
		}
	}

	return trim_list;
}
*/

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

/*
var isoLangs = {
	    "ab":{
	        "name":"Abkhaz",
	        "nativeName":"аҧсуа"
	    },
	    "aa":{
	        "name":"Afar",
	        "nativeName":"Afaraf"
	    },
	    "af":{
	        "name":"Afrikaans",
	        "nativeName":"Afrikaans"
	    },
	    "ak":{
	        "name":"Akan",
	        "nativeName":"Akan"
	    },
	    "sq":{
	        "name":"Albanian",
	        "nativeName":"Shqip"
	    },
	    "am":{
	        "name":"Amharic",
	        "nativeName":"አማርኛ"
	    },
	    "ar":{
	        "name":"Arabic",
	        "nativeName":"العربية"
	    },
	    "an":{
	        "name":"Aragonese",
	        "nativeName":"Aragonés"
	    },
	    "hy":{
	        "name":"Armenian",
	        "nativeName":"Հայերեն"
	    },
	    "as":{
	        "name":"Assamese",
	        "nativeName":"অসমীয়া"
	    },
	    "av":{
	        "name":"Avaric",
	        "nativeName":"авар мацӀ, магӀарул мацӀ"
	    },
	    "ae":{
	        "name":"Avestan",
	        "nativeName":"avesta"
	    },
	    "ay":{
	        "name":"Aymara",
	        "nativeName":"aymar aru"
	    },
	    "az":{
	        "name":"Azerbaijani",
	        "nativeName":"azərbaycan dili"
	    },
	    "bm":{
	        "name":"Bambara",
	        "nativeName":"bamanankan"
	    },
	    "ba":{
	        "name":"Bashkir",
	        "nativeName":"башҡорт теле"
	    },
	    "eu":{
	        "name":"Basque",
	        "nativeName":"euskara, euskera"
	    },
	    "be":{
	        "name":"Belarusian",
	        "nativeName":"Беларуская"
	    },
	    "bn":{
	        "name":"Bengali",
	        "nativeName":"বাংলা"
	    },
	    "bh":{
	        "name":"Bihari",
	        "nativeName":"भोजपुरी"
	    },
	    "bi":{
	        "name":"Bislama",
	        "nativeName":"Bislama"
	    },
	    "bs":{
	        "name":"Bosnian",
	        "nativeName":"bosanski jezik"
	    },
	    "br":{
	        "name":"Breton",
	        "nativeName":"brezhoneg"
	    },
	    "bg":{
	        "name":"Bulgarian",
	        "nativeName":"български език"
	    },
	    "my":{
	        "name":"Burmese",
	        "nativeName":"ဗမာစာ"
	    },
	    "ca":{
	        "name":"Catalan; Valencian",
	        "nativeName":"Català"
	    },
	    "ch":{
	        "name":"Chamorro",
	        "nativeName":"Chamoru"
	    },
	    "ce":{
	        "name":"Chechen",
	        "nativeName":"нохчийн мотт"
	    },
	    "ny":{
	        "name":"Chichewa; Chewa; Nyanja",
	        "nativeName":"chiCheŵa, chinyanja"
	    },
	    "zh":{
	        "name":"Chinese",
	        "nativeName":"中文 (Zhōngwén), 汉语, 漢語"
	    },
	    "cv":{
	        "name":"Chuvash",
	        "nativeName":"чӑваш чӗлхи"
	    },
	    "kw":{
	        "name":"Cornish",
	        "nativeName":"Kernewek"
	    },
	    "co":{
	        "name":"Corsican",
	        "nativeName":"corsu, lingua corsa"
	    },
	    "cr":{
	        "name":"Cree",
	        "nativeName":"ᓀᐦᐃᔭᐍᐏᐣ"
	    },
	    "hr":{
	        "name":"Croatian",
	        "nativeName":"hrvatski"
	    },
	    "cs":{
	        "name":"Czech",
	        "nativeName":"česky, čeština"
	    },
	    "da":{
	        "name":"Danish",
	        "nativeName":"dansk"
	    },
	    "dv":{
	        "name":"Divehi; Dhivehi; Maldivian;",
	        "nativeName":"ދިވެހި"
	    },
	    "nl":{
	        "name":"Dutch",
	        "nativeName":"Nederlands, Vlaams"
	    },
	    "en":{
	        "name":"English",
	        "nativeName":"English"
	    },
	    "eo":{
	        "name":"Esperanto",
	        "nativeName":"Esperanto"
	    },
	    "et":{
	        "name":"Estonian",
	        "nativeName":"eesti, eesti keel"
	    },
	    "ee":{
	        "name":"Ewe",
	        "nativeName":"Eʋegbe"
	    },
	    "fo":{
	        "name":"Faroese",
	        "nativeName":"føroyskt"
	    },
	    "fj":{
	        "name":"Fijian",
	        "nativeName":"vosa Vakaviti"
	    },
	    "fi":{
	        "name":"Finnish",
	        "nativeName":"suomi, suomen kieli"
	    },
	    "fr":{
	        "name":"French",
	        "nativeName":"français, langue française"
	    },
	    "ff":{
	        "name":"Fula; Fulah; Pulaar; Pular",
	        "nativeName":"Fulfulde, Pulaar, Pular"
	    },
	    "gl":{
	        "name":"Galician",
	        "nativeName":"Galego"
	    },
	    "ka":{
	        "name":"Georgian",
	        "nativeName":"ქართული"
	    },
	    "de":{
	        "name":"German",
	        "nativeName":"Deutsch"
	    },
	    "el":{
	        "name":"Greek, Modern",
	        "nativeName":"Ελληνικά"
	    },
	    "gn":{
	        "name":"Guaraní",
	        "nativeName":"Avañeẽ"
	    },
	    "gu":{
	        "name":"Gujarati",
	        "nativeName":"ગુજરાતી"
	    },
	    "ht":{
	        "name":"Haitian; Haitian Creole",
	        "nativeName":"Kreyòl ayisyen"
	    },
	    "ha":{
	        "name":"Hausa",
	        "nativeName":"Hausa, هَوُسَ"
	    },
	    "he":{
	        "name":"Hebrew (modern)",
	        "nativeName":"עברית"
	    },
	    "hz":{
	        "name":"Herero",
	        "nativeName":"Otjiherero"
	    },
	    "hi":{
	        "name":"Hindi",
	        "nativeName":"हिन्दी, हिंदी"
	    },
	    "ho":{
	        "name":"Hiri Motu",
	        "nativeName":"Hiri Motu"
	    },
	    "hu":{
	        "name":"Hungarian",
	        "nativeName":"Magyar"
	    },
	    "ia":{
	        "name":"Interlingua",
	        "nativeName":"Interlingua"
	    },
	    "id":{
	        "name":"Indonesian",
	        "nativeName":"Bahasa Indonesia"
	    },
	    "ie":{
	        "name":"Interlingue",
	        "nativeName":"Originally called Occidental; then Interlingue after WWII"
	    },
	    "ga":{
	        "name":"Irish",
	        "nativeName":"Gaeilge"
	    },
	    "ig":{
	        "name":"Igbo",
	        "nativeName":"Asụsụ Igbo"
	    },
	    "ik":{
	        "name":"Inupiaq",
	        "nativeName":"Iñupiaq, Iñupiatun"
	    },
	    "in":{
	    	"name":"Indonesian",
	    	"nativeName":"Bahasa Indonesia"
	    },
	    "io":{
	        "name":"Ido",
	        "nativeName":"Ido"
	    },
	    "is":{
	        "name":"Icelandic",
	        "nativeName":"Íslenska"
	    },
	    "it":{
	        "name":"Italian",
	        "nativeName":"Italiano"
	    },
	    "iu":{
	        "name":"Inuktitut",
	        "nativeName":"ᐃᓄᒃᑎᑐᑦ"
	    },
	    "ja":{
	        "name":"Japanese",
	        "nativeName":"日本語 (にほんご／にっぽんご)"
	    },
	    "jv":{
	        "name":"Javanese",
	        "nativeName":"basa Jawa"
	    },
	    "kl":{
	        "name":"Kalaallisut, Greenlandic",
	        "nativeName":"kalaallisut, kalaallit oqaasii"
	    },
	    "kn":{
	        "name":"Kannada",
	        "nativeName":"ಕನ್ನಡ"
	    },
	    "kr":{
	        "name":"Kanuri",
	        "nativeName":"Kanuri"
	    },
	    "ks":{
	        "name":"Kashmiri",
	        "nativeName":"कश्मीरी, كشميري‎"
	    },
	    "kk":{
	        "name":"Kazakh",
	        "nativeName":"Қазақ тілі"
	    },
	    "km":{
	        "name":"Khmer",
	        "nativeName":"ភាសាខ្មែរ"
	    },
	    "ki":{
	        "name":"Kikuyu, Gikuyu",
	        "nativeName":"Gĩkũyũ"
	    },
	    "rw":{
	        "name":"Kinyarwanda",
	        "nativeName":"Ikinyarwanda"
	    },
	    "ky":{
	        "name":"Kirghiz, Kyrgyz",
	        "nativeName":"кыргыз тили"
	    },
	    "kv":{
	        "name":"Komi",
	        "nativeName":"коми кыв"
	    },
	    "kg":{
	        "name":"Kongo",
	        "nativeName":"KiKongo"
	    },
	    "ko":{
	        "name":"Korean",
	        "nativeName":"한국어 (韓國語), 조선말 (朝鮮語)"
	    },
	    "ku":{
	        "name":"Kurdish",
	        "nativeName":"Kurdî, كوردی‎"
	    },
	    "kj":{
	        "name":"Kwanyama, Kuanyama",
	        "nativeName":"Kuanyama"
	    },
	    "la":{
	        "name":"Latin",
	        "nativeName":"latine, lingua latina"
	    },
	    "lb":{
	        "name":"Luxembourgish, Letzeburgesch",
	        "nativeName":"Lëtzebuergesch"
	    },
	    "lg":{
	        "name":"Luganda",
	        "nativeName":"Luganda"
	    },
	    "li":{
	        "name":"Limburgish, Limburgan, Limburger",
	        "nativeName":"Limburgs"
	    },
	    "ln":{
	        "name":"Lingala",
	        "nativeName":"Lingála"
	    },
	    "lo":{
	        "name":"Lao",
	        "nativeName":"ພາສາລາວ"
	    },
	    "lt":{
	        "name":"Lithuanian",
	        "nativeName":"lietuvių kalba"
	    },
	    "lu":{
	        "name":"Luba-Katanga",
	        "nativeName":""
	    },
	    "lv":{
	        "name":"Latvian",
	        "nativeName":"latviešu valoda"
	    },
	    "gv":{
	        "name":"Manx",
	        "nativeName":"Gaelg, Gailck"
	    },
	    "mk":{
	        "name":"Macedonian",
	        "nativeName":"македонски јазик"
	    },
	    "mg":{
	        "name":"Malagasy",
	        "nativeName":"Malagasy fiteny"
	    },
	    "ms":{
	        "name":"Malay",
	        "nativeName":"bahasa Melayu, بهاس ملايو‎"
	    },
	    "ml":{
	        "name":"Malayalam",
	        "nativeName":"മലയാളം"
	    },
	    "mt":{
	        "name":"Maltese",
	        "nativeName":"Malti"
	    },
	    "mi":{
	        "name":"Māori",
	        "nativeName":"te reo Māori"
	    },
	    "mr":{
	        "name":"Marathi (Marāṭhī)",
	        "nativeName":"मराठी"
	    },
	    "mh":{
	        "name":"Marshallese",
	        "nativeName":"Kajin M̧ajeļ"
	    },
	    "mn":{
	        "name":"Mongolian",
	        "nativeName":"монгол"
	    },
	    "na":{
	        "name":"Nauru",
	        "nativeName":"Ekakairũ Naoero"
	    },
	    "nv":{
	        "name":"Navajo, Navaho",
	        "nativeName":"Diné bizaad, Dinékʼehǰí"
	    },
	    "nb":{
	        "name":"Norwegian Bokmål",
	        "nativeName":"Norsk bokmål"
	    },
	    "nd":{
	        "name":"North Ndebele",
	        "nativeName":"isiNdebele"
	    },
	    "ne":{
	        "name":"Nepali",
	        "nativeName":"नेपाली"
	    },
	    "ng":{
	        "name":"Ndonga",
	        "nativeName":"Owambo"
	    },
	    "nn":{
	        "name":"Norwegian Nynorsk",
	        "nativeName":"Norsk nynorsk"
	    },
	    "no":{
	        "name":"Norwegian",
	        "nativeName":"Norsk"
	    },
	    "ii":{
	        "name":"Nuosu",
	        "nativeName":"ꆈꌠ꒿ Nuosuhxop"
	    },
	    "nr":{
	        "name":"South Ndebele",
	        "nativeName":"isiNdebele"
	    },
	    "oc":{
	        "name":"Occitan",
	        "nativeName":"Occitan"
	    },
	    "oj":{
	        "name":"Ojibwe, Ojibwa",
	        "nativeName":"ᐊᓂᔑᓈᐯᒧᐎᓐ"
	    },
	    "cu":{
	        "name":"Old Church Slavonic, Church Slavic, Church Slavonic, Old Bulgarian, Old Slavonic",
	        "nativeName":"ѩзыкъ словѣньскъ"
	    },
	    "om":{
	        "name":"Oromo",
	        "nativeName":"Afaan Oromoo"
	    },
	    "or":{
	        "name":"Oriya",
	        "nativeName":"ଓଡ଼ିଆ"
	    },
	    "os":{
	        "name":"Ossetian, Ossetic",
	        "nativeName":"ирон æвзаг"
	    },
	    "pa":{
	        "name":"Panjabi, Punjabi",
	        "nativeName":"ਪੰਜਾਬੀ, پنجابی‎"
	    },
	    "pi":{
	        "name":"Pāli",
	        "nativeName":"पाऴि"
	    },
	    "fa":{
	        "name":"Persian",
	        "nativeName":"فارسی"
	    },
	    "pl":{
	        "name":"Polish",
	        "nativeName":"polski"
	    },
	    "ps":{
	        "name":"Pashto, Pushto",
	        "nativeName":"پښتو"
	    },
	    "pt":{
	        "name":"Portuguese",
	        "nativeName":"Português"
	    },
	    "qu":{
	        "name":"Quechua",
	        "nativeName":"Runa Simi, Kichwa"
	    },
	    "rm":{
	        "name":"Romansh",
	        "nativeName":"rumantsch grischun"
	    },
	    "rn":{
	        "name":"Kirundi",
	        "nativeName":"kiRundi"
	    },
	    "ro":{
	        "name":"Romanian, Moldavian, Moldovan",
	        "nativeName":"română"
	    },
	    "ru":{
	        "name":"Russian",
	        "nativeName":"русский язык"
	    },
	    "sa":{
	        "name":"Sanskrit (Saṁskṛta)",
	        "nativeName":"संस्कृतम्"
	    },
	    "sc":{
	        "name":"Sardinian",
	        "nativeName":"sardu"
	    },
	    "sd":{
	        "name":"Sindhi",
	        "nativeName":"सिन्धी, سنڌي، سندھی‎"
	    },
	    "se":{
	        "name":"Northern Sami",
	        "nativeName":"Davvisámegiella"
	    },
	    "sm":{
	        "name":"Samoan",
	        "nativeName":"gagana faa Samoa"
	    },
	    "sg":{
	        "name":"Sango",
	        "nativeName":"yângâ tî sängö"
	    },
	    "sr":{
	        "name":"Serbian",
	        "nativeName":"српски језик"
	    },
	    "gd":{
	        "name":"Scottish Gaelic; Gaelic",
	        "nativeName":"Gàidhlig"
	    },
	    "sn":{
	        "name":"Shona",
	        "nativeName":"chiShona"
	    },
	    "si":{
	        "name":"Sinhala, Sinhalese",
	        "nativeName":"සිංහල"
	    },
	    "sk":{
	        "name":"Slovak",
	        "nativeName":"slovenčina"
	    },
	    "sl":{
	        "name":"Slovene",
	        "nativeName":"slovenščina"
	    },
	    "so":{
	        "name":"Somali",
	        "nativeName":"Soomaaliga, af Soomaali"
	    },
	    "st":{
	        "name":"Southern Sotho",
	        "nativeName":"Sesotho"
	    },
	    "es":{
	        "name":"Spanish; Castilian",
	        "nativeName":"español, castellano"
	    },
	    "su":{
	        "name":"Sundanese",
	        "nativeName":"Basa Sunda"
	    },
	    "sw":{
	        "name":"Swahili",
	        "nativeName":"Kiswahili"
	    },
	    "ss":{
	        "name":"Swati",
	        "nativeName":"SiSwati"
	    },
	    "sv":{
	        "name":"Swedish",
	        "nativeName":"svenska"
	    },
	    "ta":{
	        "name":"Tamil",
	        "nativeName":"தமிழ்"
	    },
	    "te":{
	        "name":"Telugu",
	        "nativeName":"తెలుగు"
	    },
	    "tg":{
	        "name":"Tajik",
	        "nativeName":"тоҷикӣ, toğikī, تاجیکی‎"
	    },
	    "th":{
	        "name":"Thai",
	        "nativeName":"ไทย"
	    },
	    "ti":{
	        "name":"Tigrinya",
	        "nativeName":"ትግርኛ"
	    },
	    "bo":{
	        "name":"Tibetan Standard, Tibetan, Central",
	        "nativeName":"བོད་ཡིག"
	    },
	    "tk":{
	        "name":"Turkmen",
	        "nativeName":"Türkmen, Түркмен"
	    },
	    "tl":{
	        "name":"Tagalog",
	        "nativeName":"Wikang Tagalog, ᜏᜒᜃᜅ᜔ ᜆᜄᜎᜓᜄ᜔"
	    },
	    "tn":{
	        "name":"Tswana",
	        "nativeName":"Setswana"
	    },
	    "to":{
	        "name":"Tonga (Tonga Islands)",
	        "nativeName":"faka Tonga"
	    },
	    "tr":{
	        "name":"Turkish",
	        "nativeName":"Türkçe"
	    },
	    "ts":{
	        "name":"Tsonga",
	        "nativeName":"Xitsonga"
	    },
	    "tt":{
	        "name":"Tatar",
	        "nativeName":"татарча, tatarça, تاتارچا‎"
	    },
	    "tw":{
	        "name":"Twi",
	        "nativeName":"Twi"
	    },
	    "ty":{
	        "name":"Tahitian",
	        "nativeName":"Reo Tahiti"
	    },
	    "ug":{
	        "name":"Uighur, Uyghur",
	        "nativeName":"Uyƣurqə, ئۇيغۇرچە‎"
	    },
	    "uk":{
	        "name":"Ukrainian",
	        "nativeName":"українська"
	    },
	    "ur":{
	        "name":"Urdu",
	        "nativeName":"اردو"
	    },
	    "uz":{
	        "name":"Uzbek",
	        "nativeName":"zbek, Ўзбек, أۇزبېك‎"
	    },
	    "ve":{
	        "name":"Venda",
	        "nativeName":"Tshivenḓa"
	    },
	    "vi":{
	        "name":"Vietnamese",
	        "nativeName":"Tiếng Việt"
	    },
	    "vo":{
	        "name":"Volapük",
	        "nativeName":"Volapük"
	    },
	    "wa":{
	        "name":"Walloon",
	        "nativeName":"Walon"
	    },
	    "cy":{
	        "name":"Welsh",
	        "nativeName":"Cymraeg"
	    },
	    "wo":{
	        "name":"Wolof",
	        "nativeName":"Wollof"
	    },
	    "fy":{
	        "name":"Western Frisian",
	        "nativeName":"Frysk"
	    },
	    "xh":{
	        "name":"Xhosa",
	        "nativeName":"isiXhosa"
	    },
	    "yi":{
	        "name":"Yiddish",
	        "nativeName":"ייִדיש"
	    },
	    "yo":{
	        "name":"Yoruba",
	        "nativeName":"Yorùbá"
	    },
	    "za":{
	        "name":"Zhuang, Chuang",
	        "nativeName":"Saɯ cueŋƅ, Saw cuengh"
	    }
};
*/

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
