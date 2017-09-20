class Class
  def attr_accessor_with_history(attr_name)
    attr_name = attr_name.to_s # make sure it's a string
    attr_reader attr_name # create the attribute's getter
    attr_reader attr_name+"_history" # create bar_history getter
    
    class_eval %Q{
      def #{attr_name}=(value) #this one stores current values as well, unless the instructions below are followed
       @#{attr_name}_history ||= [nil]
=begin Comment out statement above and uncomment this to store only PREVIOUS values in the history array
       if !defined? @#{attr_name}_history
        @#{attr_name}_history = [nil]
      else
        @#{attr_name}_history.push(@#{attr_name})
      end
=end
        @#{attr_name} = value #set value
        @#{attr_name}_history.push(value) #comment this out if you're bringing the if else back.
      end
    }
    
  end
end
