<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>Title</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/vue.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<style>
tr {
	line-height: 45px;
}
</style>
</head>
<body>
	<div class="container"
		style="width: 400px; margin-top: 100px; padding-bottom: 20px; border: 1px solid #ddd; border-radius: 5px">
		<h2>新建客户</h2>
		<hr>
		<div style="margin: 0 auto;">
			<form id="initForm">
				<table class="profile-table" style="width: 100%">
					<tr>
						<th>客户名称</th>
						<td><input class="form-control" id="customName"
							name="customName" type="text"></td>
					</tr>

					<tr>
						<th>客户来源</th>
						<td><select class="form-control" name="sourceId">
								<option disabled selected>-请选择客户来源-</option>
								<option v-bind:value="source.sourceId" v-for="source in sources">{{source.sourceName}}</option>
						</select></td>
					</tr>
					<tr>
						<th>所属行业</th>
						<td><select class="form-control" name="tradeId">
								<option disabled selected>-请选择行业-</option>
								<option v-bind:value="trade.tradeId" v-for="trade in trades">{{trade.tradeName}}</option>
						</select></td>
					</tr>
					<tr>
						<th>客户级别</th>
						<td><select class="form-control" name="levelId">
								<option disabled selected>-请选择客户级别-</option>
								<option v-bind:value="level.levelId" v-for="level in levels">{{level.levelName}}</option>
						</select></td>
					</tr>
					<tr>
						<th>联系人</th>
						<td><input class="form-control" id="contacts" name="contacts"
							type="text"></td>
					</tr>
					<tr>
						<th>固定电话</th>
						<td><input class="form-control" id="telephone"
							name="telephone" type="text"></td>
					</tr>
					<tr>
						<th>移动电话</th>
						<td><input class="form-control" id="phone" name="phone"
							type="text"></td>
					</tr>
					<tr>
						<th>邮编</th>
						<td><input class="form-control" id="postCode" name="postCode"
							type="text"></td>
					</tr>
					<tr>
						<th>聯係地址</th>
						<td><input class="form-control" id="address" name="address"
							type="text"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button v-on:click="sendFrom()" type="button"
								class="btn btn-success" style="width: 100%; margin-top: 10px">新建客户</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
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
			},
			sendFrom : function() {
				var _self = this;
				$.ajax({
					type : 'POST',
					data:$("#initForm").serialize(),
					url : './regCustom.do',
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
</script>
</html>