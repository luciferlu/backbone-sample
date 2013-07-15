(function($, backbone){
    var User = backbone.Model.extend({
        urlRoot: "/service/users",

        initialize: function(){
            console.log("model created");
            this.bind("change:name", function(){
                console.log("name is changing");
            });
        },

        defaults: {
            name: "tanxianhu",
            age: "5"
        }
    });

    var UserEditor = backbone.View.extend({
        events:{
            "click #btnOk":     "save"
        },

        initialize: function(options){
            this.user = options.user;
            this.listenTo(this.user, "sync", this.render);
        },

        render: function(){
            this.$el.html(_.template($("#userEditTemplate").html(), this.user.toJSON()));
            return this;
        },

        save: function(){
            console.log(this.$("#name").val());
            this.user.set({name: this.$("#name").val()});
            this.user.save();
        }
    });

    var App = backbone.Router.extend({
        routes:{
            "":         "home",
            "start":    "startApp"
        },

        home: function(){

        },

        startApp: function(){
            var user = new User({id: "0001"});
            var userEditor = new UserEditor({user: user});
            user.fetch();
            $("#app-container").html(userEditor.el);
        }
    });

    this.App = App;
})($, Backbone);
