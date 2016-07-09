$(function(){

    var Record = Backbone.Model.extend({
        defaults : function(){
            return {
                "album": "",
                "artist": "",
                "review": "",
                "island": false,
                "date_created": Date(),
                "date_updated" : Date(),
                "user_id" : null,
                "recommender_id": null,
                "listened_to": false,
            }
        },
    });
    
    var User = Backbone.Model.extend({
        defaults : function(){
            return {
                "name": "",
                "location": ""
            }
        }
    });
    
    var RecordCollection = Backbone.Collection.extend({
        
        model: Record,
        url: "/record",
        parse: function(response){
            return response.records;
	    }
        
    });
    var recordCollection = new RecordCollection;
    
    var AppRouter = Backbone.Router.extend({
        routes : {
            "hello_world": "hello_world",
        },
        
        hello_world : function(){
            console.log("Hello world!");
        },
    });
    var appRouter = new AppRouter;
    
    
    var AppView = Backbone.View.extend({
       
       el: $("#appview"),
       
       events : {
           //these events are for DOM events
       },
       
       initialize : function() {
           //events from other Backbone objects
           console.log("initialized appview");
           this.listenTo(appRouter, "route:hello_world", this.hello_world);   
       },
       
       hello_world : function(){
           console.log("Well hi there");
       },
       
    });
    var appView = new AppView;
    
    Backbone.history.start();

});


