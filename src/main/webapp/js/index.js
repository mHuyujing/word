var showCustoms = new Vue({
		el : '#customs',
		data : {
			customs : ""
		},
		methods : {
			showData : function() {
				var _self = this;
				$.ajax({
					type : 'GET',
					url : './allCustom.do',
					success : function(data) {
						_self.customs = data;
						/* showCustoms.$data.customs.push(showCustoms.$data.customs[1]) */
					}
				});
			}
		}
	});
var showPageNum = new Vue({
	el : '#showPageNum',
	data : {
		PageNum : ''
	},
	methods : {
		getPageTotle : function() {
			var _self = this;
			$.ajax({
				type : 'POST',
				url : './getPageTotle.do',
				success : function(data) {
					_self.PageNum = data;
				}
			});
		},
		toPage:function(page) {
			var sendData = "&page=" + page;
			$.ajax({
				type : 'POST',
				url : './search.do',
				data : sendData,
				success : function(data) {
					showCustoms.customs = data;
				}
			});
		}
	}
});
	var options = new Vue({
		el : '#initForm',
		data : {
			sources : '',
			trades : '',
			levels : ''
		},
		methods : {
			getSources : function() {
				var _self = this;
				$.ajax({
					type : 'GET',
					url : './getSource.do',
					success : function(data) {
						_self.sources = data;
					}
				});
			},
			getTrades : function() {
				var _self = this;
				$.ajax({
					type : 'GET',
					url : './getTrade.do',
					success : function(data) {
						_self.trades = data;
					}
				});
			},
			getLevels : function() {
				var _self = this;
				$.ajax({
					type : 'GET',
					url : './getLevel.do',
					success : function(data) {
						_self.levels = data;
					}
				});
			}
		},
		mounted : function() {
			this.getSources();
			this.getTrades();
			this.getLevels();
		}
	});

	function searchCustom() {
		$.ajax({
			type : 'POST',
			url : './search.do',
			data : $("#searchForm").serialize(),
			success : function(data) {
				showCustoms.customs = data;
				showPageNum.getPageTotle();
			}
		});
	}

	