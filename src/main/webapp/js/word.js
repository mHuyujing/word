var nav = new Vue({
    el: '#nav',
    data: {
        wordName: "",
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
        goto: function (wordName) {
            window.location.href = './word.do?wordName=' + wordName;
        },
        enter: function () {
            window.location.href = './word.do?wordName=' + this.keyword;
        },
        blur: function () {
            var t = setTimeout("nav.searchKeyword=[];", 200);
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
    }
});
var word = new Vue({
        el: "#word",
        data: {

            note: ""
        },
        methods: {
            // getWord: function (wordName) {
            //     $.ajax({
            //         type: 'GET',
            //         url: './getWord.do',
            //         data: "&wordName=" + wordName,
            //         success: function (data) {
            //             word.wordData = data;
            //             word.getNote(word.wordData.wordId);
            //         }
            //     });
            // },
            getNote: function (wordId) {
                $.ajax({
                    type: 'GET',
                    url: './getNote.do',
                    data: "&wordId=" + wordId,
                    success: function (data) {
                        word.note = data;
                    }
                });
            }
        },
        mounted: function () {
            this.wordName = gerWordName;
            word.getNote(getWordId);
        }
    }
);