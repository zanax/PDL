<div class="footer"></div>
</div>
</div>
</div>
</div>
</body>
</html>


<script>
    window.addEvent('domready', function() {
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
    });
</script>
