/**
 * Copyright (c) 2013 Web.com Group, Inc. All Rights Reserved.
 * 
 * This is an unpublished work protected by Web.com Group, Inc. as a trade
 * secret, and is not to be used or disclosed except as expressly provided in a
 * written license agreement executed by you and Web.com Group, Inc.
 * 
 * @author Attila Mezei Horvati
 */
var webcom = {};
webcom.util = {};

webcom.util.isOpera = !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
// Opera 8.0+ (UA detection to detect Blink/v8-powered Opera)
webcom.util.isFirefox = typeof InstallTrigger !== 'undefined'; // Firefox
// 1.0+
webcom.util.isSafari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
// At least Safari 3+: "[object HTMLElementConstructor]"
webcom.util.isChrome = !!window.chrome && !webcom.util.isOpera; // Chrome
// 1+
webcom.util.isIE = /* @cc_on!@ */false || document.documentMode; // At least
// IE6

webcom.util.StringUtil = {
  isEmpty : function(str) {
    return (!str || str.length == 0);
  },

  /**
   * Method to switch a map into one or multiple lines of text: Format is:
   * key1=value1\n key2=value2 ...
   */
  mapToStr : function(map) {
    var str = "";
    if (typeof map == "object" && map != null) {
      for ( var i = 0; i < map.length; i++) {
        str += map[i]['keyField'] + "=" + map[i]['valueField'] + "\n";
      }
    }
    return str;
  },

  /**
   * Method to switch one or multiple lines of text into a map. Format is:
   * key1=value1\n key2=value2 ...
   */
  strToMap : function(str) {
    var map = [];
    if (str && typeof str == 'string') {
      var s = str.split('\n');
      for ( var i = 0; i < s.length; i++) {
        var kv = s[i].split('=');
        if (kv[0] && kv[0].length > 0) {
          map.push({
            keyField : kv[0],
            valueField : kv[1] ? kv[1] : "null"
          });
        }
      }
    }
    return map;
  },

  /**
   * Capitalize a string.
   * 
   * @param {String}
   * @return {String} capitalized
   */
  capitalize : function(str) {
    return str.replace(/[a-zA-Z]/, function(firstLetter) {
      return firstLetter.toUpperCase();
    });
  }
};

webcom.util.ObjectUtil = {
  /**
   * check if element is an array
   * 
   * @param e -
   *            element to check if array
   * @return true if array, false otherwise
   */
  isArray : function(e) {
    return (e && typeof e === 'object' && e instanceof Array);
  }
}

/**
 * webcom Timer Utility class
 */
webcom.util.timerUtil = {
  /**
   * Creates and returns a new debounced version of the passed function which
   * will postpone its execution until after `delay` milliseconds have elapsed
   * since the last time it was invoked.
   * 
   * based on http://remysharp.com/2010/07/21/throttling-function-calls/
   * 
   * @param fn -
   *            the function
   * @param delay -
   *            the delay in milliseconds
   * 
   * @return debounced function
   */
  debounce : function(fn, delay) {
    var timer = null;
    return function() {
      var context = this, args = arguments;
      clearTimeout(timer);
      timer = setTimeout(function() {
        fn.apply(context, args);
      }, delay);
    };
  }
};

/**
 * Usage
 * 
 * Register event to be called somewhere in your code
 * webcom.util.Events.register('AssetManagerOnRefresh', 'replaceImageActive', overrideAssetClick);
 * 
 * Unregister the event if you do not need it called anymore
 * webcom.util.Events.unRegister('replaceImageActive');
 * 
 * Call events when the time is right
 * for(var i=0;i<webcom.util.Events.get('AssetManagerOnRefresh').length;i++) {
 *   webcom.util.Events.get('AssetManagerOnRefresh')[i].callback();
 * }
 */
webcom.util.Events = {
    events: [],
    
    /**
     * Register neo.Event
     * @param {string} groupName ex: ClassNameOnAction or AssetManagerOnUpload
     * @param {string} callBackName ex: EventName or onReplaceImage, this is passed for the purpose of being able to remove it later
     * @returns {void}
     */
    register: function(group, name, callback) {
      console.log('Registering event: ', group + " :: " + name);
      this.events.push({group: group, name: name, callback: callback});
    },
    
    /**
     * Unregister event from stack
     * @param {string} name of callbackname that was registered
     * @returns {void}
     */
    unRegister: function(name) {
      for(var i=0;i<this.events.length;i++) {
        if(name == this.events[i].name) {
          console.log('Removing event: ', this.events[i].name);
          this.events.splice(i,1);
        }
      }
    },
    
    /**
     * Get events for specified group
     * @param {string} groupName ex: ClassNameOnAction or AssetManagerOnUpload
     * @returns {Array} array of events
     */
    getByGroup: function(group) {
      var registeredEvents = [];
      
      for(var i=0;i<this.events.length;i++) {
        if(group == this.events[i].group) {
          registeredEvents.push(this.events[i]);
        }
      }
      
      return registeredEvents;
    },
    
    /**
     * Get event by eventNam (not group)
     * @param {string} groupName ex: ClassNameOnAction or AssetManagerOnUpload
     * @returns {Array} array of events
     */
    getByName: function(name) {
      var registeredEvents = [];
      
      for(var i=0;i<this.events.length;i++) {
        if(name == this.events[i].name) {
          registeredEvents.push(this.events[i]);
        }
      }
      
      return registeredEvents;
    },
    
    /**
     * Automatically call all events for the specified groupName
     * @param {string} groupName ex: ClassNameOnAction or AssetManagerOnUpload
     * @param {object} vars ex: {my:'var',scope: this}
     * @returns {void} 
     */
    fireEvent: function(groupName, vars) {
      for(var i=0;i<webcom.util.Events.getByGroup(groupName).length;i++) {
        //console.debug('EXECUTING', groupName);
        webcom.util.Events.getByGroup(groupName)[i].callback(vars);
      }
    }
}