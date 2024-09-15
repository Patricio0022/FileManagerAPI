import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export function InputButton() {

  const [url, setUrl] = useState("");

  const handleChange = (event:any) => {
    setUrl(event.target.value);
  };


  const handleSubmit = async (event:any) => {
    event.preventDefault(); 

    try {
 
      const response = await fetch(`http://localhost:8080/api/files/txt?url=${encodeURIComponent(url)}`);
      const data = await response.text();

    
      console.log(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  return (
    <form className="flex w-full max-w-sm items-center space-x-2" onSubmit={handleSubmit}>
      <Input 
        type="text" 
        placeholder="Write your URL" 
        value={url}
        onChange={handleChange} 
      />
      <Button type="submit">Submit</Button>
    </form>
  );
}
