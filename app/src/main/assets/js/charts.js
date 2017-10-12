// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
    title: {
        text: 'ECharts 入门示例',
         x:'center'
    },
    tooltip: {},
    legend: {
        data: ['销量', 'haha']
    },
    xAxis: {
        data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }, {
        name: 'haha',
        type: 'line',
        data: [31, 79, 54, 53, 30, 13]
    }]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
function changeTitle() {
    var textContent = document.getElementById("ipt").value;
    option.title.text = textContent;
    option.legend.data.forEach(function (a, b, c) {
            c[b] = "销售额"+b;
    });

    option.series.forEach(function (a, b, c) {
            c[b] .name = "销售额"+b;
    });
    myChart.setOption(option)

}
function changeTi(xx) {
    option.title.text = xx;
//    option.legend.data.forEach(function (a, b, c) {
//            c[b] = "销售额"+b;
//    });
option.legend.data=[];
    option.series.forEach(function (a, b, c) {
            c[b] .name = "销售额"+parseInt(Math.random()*30, 10);
             option.legend.data[b]= c[b] .name ;
              option.legend.data.push(c[b].name);
             c[b].data.forEach(function (a, b, c) {
                c[b] = parseInt(Math.random()*30, 10);
             });
    });
    myChart.setOption(option)

}

