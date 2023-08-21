var _____WB$wombat$assign$function_____ = function(name) {return (self._wb_wombat && self._wb_wombat.local_init && self._wb_wombat.local_init(name)) || self[name]; };
if (!self.__WB_pmw) { self.__WB_pmw = function(obj) { this.__WB_source = obj; return this; } }
{
    let window = _____WB$wombat$assign$function_____("window");
    let self = _____WB$wombat$assign$function_____("self");
    let document = _____WB$wombat$assign$function_____("document");
    let location = _____WB$wombat$assign$function_____("location");
    let top = _____WB$wombat$assign$function_____("top");
    let parent = _____WB$wombat$assign$function_____("parent");
    let frames = _____WB$wombat$assign$function_____("frames");
    let opener = _____WB$wombat$assign$function_____("opener");

    $(function() {
        $('.header_menu a').click(function(){
            console.log('test test ');
            var target = $($(this).attr('href'))

            $("html, body").animate({ scrollTop: target.offset()['top']+"px" });

            return false;
        });

        var contactForm = $('#contact_form');
        contactForm.find('.clear_form').click(function() {
            contactForm.reset();
            return false;
        });

        contactForm.find('.send_form').click(function() {
            contactForm.submit();
            return false;
        });

        contactForm.submit(function() {
            contactForm.ajaxSubmit({
                dataType: 'json',
                success: function(response) {
                    if (response['success']) {
                        contactForm.fadeOut(function(){
                            $('#send_success').fadeIn();
                        });

                        setTimeout(function(){
                            $('#send_success').fadeOut();
                        }, 10000);
                    } else {
                        $('.error_message').remove();
                        contactForm.find('input,textarea').css({border:'none'});
                        $.each(response['errors'], function(key, value) {
                            var span = $('[name="'+key+'"]');
                            span.css({'border': '1px solid red'})
                            span.after('<span class="error_message">'+value+'</span>');
                        });
                    }
                }
            });
            return false;
        });
    });


}
