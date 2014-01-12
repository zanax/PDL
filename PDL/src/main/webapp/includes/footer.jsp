<div class="footer"></div>
</div>
</div>
</div>
</div>
</body>
</html>

<script>
    window.addEvent('domready', function() {
        addButtonEffects();
    });
    
    function addButtonEffects(){
        $$('.button').each(function(el) {
            var color = el.getStyle('backgroundColor');

            el.addEvents({
                mouseenter: function() {
                    this.morph({
                        'background-color': '#00B711',
                        'color': '#ffffff'
                    });
                },
                mouseleave: function() {
                    this.morph({
                        backgroundColor: color,
                        color: '#575757'
                    });
                }
            });
        });
        
        $$('.cancel').each(function(el) {
            var color = el.getStyle('backgroundColor');

            el.addEvents({
                mouseenter: function() {
                    this.morph({
                        'background-color': '#FF0000',
                        'color': '#ffffff'
                    });
                },
                mouseleave: function() {
                    this.morph({
                        backgroundColor: color,
                        color: '#575757'
                    });
                }
            });
        });
    }
    
    function toggleContent(){
        if($('create-chat')) $('create-chat').hide();
        $('chat-rooms').hide();
        $('chat-part').toggle();
        $('leave-chat').toggle();
    }
    
    function alterDynamicChatButtons(){
        // select the target node
        var target = $('rooms-list');

        // create an observer instance
        var observer = new MutationObserver(function(mutations) {
            mutations.forEach(function(mutation) {
                if(mutation.type === 'childList'){
                    var join_buttons = [].slice.call(target.children);
                    join_buttons.each(function(els){
                        var row = els.getChildren()[1];
                        var join_button = row.getChildren()[0];
                        
                        //Set the correct style for the button
                        join_button.set('class', 'join button right');
                        addButtonEffects();
                        
                        join_button.addEvent('click', function(){
                            toggleContent();
                        });
                    });
                    
                    //Edits have been made, stop observing!
                    observer.disconnect();
                }
            });    
        });

        // configuration of the observer:
        var config = { attributes: true, childList: true, characterData: true };

        // pass in the target node, as well as the observer options
        observer.observe(target, config);
    }
</script>
