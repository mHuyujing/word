<%--
  Created by IntelliJ IDEA.
  User: yujing
  Date: 18-7-6
  Time: 下午3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/vue.js"></script>
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script>
    <style>
        .keywordList {
            z-index: 1;
        }

        .keywordList li {
            list-style-type: none;
        }

        .keywordList li:hover {
            cursor: pointer;
            background: #d6e9c6;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" id="nav">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">WORD QU</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">单词 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">例句</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${user != null}">
                        <li>
                            <a href="./user.html">
                                    ${user.getUserName()}
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="./login.html">用户登陆</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">操作 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">用户信息</a></li>
                        <li><a href="#">设置</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="form-group" id="search" style="text-align: center">
        <h3><i class="glyphicon glyphicon-search"></i>单词检索</h3>
        <input autocomplete="off" type="text" class="form-control" v-model="wordName" placeholder="Search"
               style="width: 300px;display: initial;"
               name="wordName"
               @keyup="get($event)"
               @keydown.down.prevent="selectDown" @keydown.up.prevent="selectUp" @blur.prevent="blur">
        <button class="btn btn-info" v-on:v-on:click="enter()">搜索</button>

        <ul class="keywordList" style="    width: 300px;
    text-align: left;
    position: absolute;
    background-color: rgb(212, 212, 212);
    left: calc(50% - 180px);
    padding-left: 15px;">
            <li v-for="key in searchKeyword" v-on:click="goto(key.wordId)">
                {{key.wordName}}
            </li>
        </ul>
    </div>
</div>

</body>

<script type="text/javascript">
    var search = new Vue({
        el: '#search',
        data: {
            wordName: "${wordName}",
            userName: "HYJ",
            searchKeyword: [],
            keyword: "",
            now: -1
        },
        methods: {
            get: function (event) {
                if (event.keyCode === 38 || event.keyCode === 40) return;
                if (event.keyCode === 13) {
                    window.location.href = './word.do?wordName=' + this.keyword;
                }
            },
            selectDown: function () {
                this.now++;
                if (this.now === this.searchKeyword.length) this.now = -1;
                if (this.now === -1) {
                    this.keyword = this.wordName;
                    $(".keywordList li").css("background", "rgb(212, 212, 200)");
                } else {
                    this.keyword = this.searchKeyword[this.now].wordName;
                    $(".keywordList li").css("background", "rgb(212, 212, 200)");
                    $(".keywordList").find("li").eq(this.now).css("background", "red");
                }
            },
            selectUp: function () {
                this.now--;
                if (this.now === -2) this.now = this.searchKeyword.length - 1;
                this.keyword = this.searchKeyword[this.now].wordName;
                if (this.now === -1) {
                    $(".keywordList li").css("background", "rgb(212, 212, 200)");
                } else {
                    $(".keywordList li").css("background", "rgb(212, 212, 200)");
                    $(".keywordList").find("li").eq(this.now).css("background", "red");
                }
            },
            search: function (key) {
                // 获取下拉关键字
                var _self = this;
                $.ajax({
                    type: 'GET',
                    url: './getSearchKey.do',
                    data: "&wordName=" + key,
                    success: function (data) {
                        _self.searchKeyword = data;
                    }
                });
            },
            goto: function (wordId) {
                // 转跳到指定的wordId
                window.location.href = './word.do?wordId=' + wordId;
            },
            enter: function () {
                // 转跳到输入框的
                window.location.href = './word.do?wordName=' + this.keyword;
            },
            blur: function () {
                // 失去焦点的后搜索栏延时消失
                var t = setTimeout("search.searchKeyword=[];", 200);
            }
        },
        watch: {
            wordName: function (val) {
                if (val !== "") {
                    this.keyword = val;
                    this.search(val);
                } else {
                    this.searchKeyword = [];
                }
            }
        },
    });
</script>
</html>
