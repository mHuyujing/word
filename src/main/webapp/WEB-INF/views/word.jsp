<%@ page import="org.hyj.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Word</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
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

        #word ul li {
            font-size: 16px;
            line-height: 30px;
            list-style-type: decimal;
        }

        #word .h3 .glyphicon {
            font-size: 10px;
            margin-right: 10px;
            color: #099f93;
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
            <div class="navbar-form navbar-left">
                <div class="form-group">
                    <input autocomplete="off" type="text" class="form-control" v-model="wordName" placeholder="Search"
                           name="wordName"
                           @keyup="get($event)"
                           @keydown.down.prevent="selectDown" @keydown.up.prevent="selectUp" @blur.prevent="blur">
                    <ul class="keywordList" style="width:214px;position: absolute;
                    background-color: #d4d4d4;
                        padding-left: 15px;">
                        <li v-for="key in searchKeyword" v-on:click="goto(key.wordName)">
                            {{key.wordName}}
                        </li>
                    </ul>
                    <button class="btn btn-info" v-on:v-on:click="enter()">搜索</button>
                </div>
            </div>
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
                        <li><a href="./word.jsp">用户退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container" id="word">
    <div>
        <span class="h3">{{wordData.wordName}}</span>
        <span class="h5">{{wordData.wordPron}}</span>
    </div>
    <hr>
    <div class="row" style="">
        <div class="col-md-10">
            <div class="col-md-6">
                <div>
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>释义
                    </div>
                    <div class="h5">
                        <ul>
                            <li v-for="meaning in wordData.wordSimpleMeaning" v-if="meaning != '[]' && meaning != ''">
                                {{meaning}}
                            </li>
                        </ul>
                    </div>
                </div>
                <div>
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>详细释义 英语解释
                    </div>
                    <div class="h5">
                        <ul>
                            <li v-for="meaning in wordData.wordDoubleMeaning">{{meaning}}</li>
                        </ul>
                    </div>
                </div>
                <hr>
                <div>
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>例句
                    </div>
                    <div class="h5">
                        <ul>
                            <li v-for="words in wordData.wordWords">
                                {{words}}
                            </li>
                        </ul>
                    </div>
                </div>
                <hr>

                <div v-if="wordData.wordDetail != '[]'">
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>词源说明
                    </div>
                    <div class="h5">
                        <ul>
                            <li v-for="detail in wordData.wordDetail">
                                {{detail}}
                            </li>
                        </ul>
                    </div>
                </div>
                <hr>

            </div>
            <div class="col-md-6">
                <c:choose>
                    <c:when test="${user != null}">
                        <div v-if="note!=null">
                            <div class="h3">
                                我的笔记
                                <button class="btn btn-primary" onclick="updateNote()" type="button" style="font-size: 12px">
                                    修改笔记
                                </button>
                            </div>
                            <div class="h5" style="border-left: 4px solid #28a4c9;padding-left: 10px">
                                {{note.noteText}}
                            </div>
                        </div>
                        <div v-if="note==null">
                            <button class="btn btn-primary" type="button" onclick="insertNote()">添加笔记</button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="./login.html">登陆后添加笔记</a>
                    </c:otherwise>
                </c:choose>

                <div v-if="wordSimilar != '[]'">
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>形近词
                    </div>
                    <div class="h5">
                        <ul style="-webkit-padding-start: 10px">
                            <span v-for="similar in wordData.wordSimilar" style="margin-left: 20px">
                               <span style="margin-top:10px" class="badge">{{similar}}</span>
                            </span>
                        </ul>
                    </div>
                </div>
                <div v-if="wordData.wordDeri != '[]'">
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>派生词
                    </div>
                    <div class="h5">
                        {{wordDeri}}
                    </div>
                </div>
                <div v-if="wordData.wordSyno != '[]'">
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>近义词
                    </div>
                    <div class="h5" v-for="syno in wordData.wordSyno">
                        {{syno}}
                    </div>
                </div>
                <div v-if="wordData.wordAnto!='[]'">
                    <div class="h3">
                        <i class="glyphicon glyphicon-chevron-right"></i>反义词
                    </div>
                    <div class="h5">
                        {{wordData.wordAnto}}
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <h3>单词序列</h3>
            <ul>
                <li>a</li>
                <li>a</li>
                <li>a</li>
                <li>a</li>
                <li>a</li>
            </ul>
            <div>
                <ul class="pagination">
                    <li><a href="#" aria-label="Previous"> <span aria-hidden="true">«</span>
                    </a></li>
                    <li><a href="#" aria-label="Next"> <span aria-hidden="true">»</span>
                    </a></li>
                </ul>
            </div>
        </div>
    </div>
    <hr>
    <div class="container">
        <div class="row">
            <div class="col-md-8">

            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    var gerWordName = "${wordName}";
</script>
<script type="text/javascript" src="./js/word.js"></script>
<script type="text/javascript">
    function insertNote() {
        var content = prompt("请输入笔记内容");
        if (content !== null&& "" !== content) {
            $.ajax({
                type: 'POST',
                url: './insertNote.do',
                data: "&wordId=" +word.wordData.wordId+"&noteText="+content,
                success: function (data) {
                    if (data===1) {
                        alert("修改成功！");
                        word.getNote(word.wordData.wordId);
                    }else {
                        alert("错误");
                    }
                }
            });
        }
    }
    function updateNote() {
        var content = prompt("请输入新的笔记",word.note.noteText);
        if (content !== null&& "" !== content) {
            $.ajax({
                type: 'POST',
                url: './updateNote.do',
                data: "&wordId=" +word.wordData.wordId+"&noteText="+content,
                success: function (data) {
                    if (data===1) {
                        alert("修改成功！");
                        word.getNote(word.wordData.wordId);
                    }else {
                        alert("错误");
                    }
                }
            });
        }
    }
</script>
</html>
